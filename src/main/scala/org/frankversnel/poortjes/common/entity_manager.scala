package org.frankversnel.poortjes

import scala.collection.mutable.ListBuffer
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._

class EntityManager private(val renderer: Renderer) extends Logging {

	private var gameObjects = ListBuffer[GameObject]()

	private val componentManagers = List(
			new CollisionManager,
			new RenderingManager(renderer),
			new ComponentProcessingManager
		)

    def spawn(gameObject: GameObject) {
		gameObjects += gameObject

		onGameObjectAndChildren(gameObject) { gameObject =>
			val component = gameObject.asInstanceOf[Component]
			componentManagers.foreach(_.addComponent(component))
		}
	}

    def process {
		componentManagers.foreach(_.processComponents)
    }

	def cleanUp {
		val destroyedGameObjects = gameObjects.filter(_.isDestroyed).readOnly
		gameObjects --= destroyedGameObjects

		destroyedGameObjects.foreach { destroyedGameObject =>
			onGameObjectAndChildren(destroyedGameObject) { gameObject =>
				componentManagers.foreach(_.removeComponent(gameObject.asInstanceOf[Component]))
			}
		}
	}

	private def onGameObjectAndChildren(gameObject: GameObject)(apply: GameObject => Unit) {
		apply(gameObject)
		gameObject.children.foreach(onGameObjectAndChildren _)
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
