package org.frankversnel.poortjes

import akka.stm._
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._

class EntityManager private(val renderer: Renderer) extends Logging {

	private val componentManagers = List(
			new CollisionManager, 
			new RenderingManager(renderer)
		)

    private val gameObjects = Ref(List[GameObject]())

    def spawn(gameObject: GameObject) {
        // add game object
        gameObjects alter (gameObject :: _)

		val component = gameObject.asInstanceOf[Component]
		componentManagers.foreach(_.addComponent(component))
	}

    def process {
		componentManagers.foreach(_.processComponents)
    }
}
object EntityManager {
    var entityManager: Option[EntityManager] = None
    def initialize(renderer: Renderer) {
        entityManager = Some(new EntityManager(renderer))
    }

    def apply() = {
        require(entityManager.isDefined, "Entity manager must be initialized first")
        entityManager.get
    }
}
