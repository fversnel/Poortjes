package org.frankversnel.poortjes

import akka.actor.Actor._
import akka.actor.ActorRef
import akka.stm._
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._
import ComponentManager._

class EntityManager private(val renderer: Renderer) extends Logging {

    private val renderingManager = actorOf(new RenderingManager(renderer)).start
    private val collisionManager = actorOf(new CollisionManager).start
    private val componentManagers = List(renderingManager, collisionManager)
    private val otherComponentManagers = componentManagers.filterNot(_.equals(renderingManager))

    private val gameObjects = Ref(List[GameObject]())

    def spawn(gameObject: GameObject) {
        // add game object
        gameObjects alter (gameObject :: _)

        componentManagers.foreach { componentManager =>
        componentManager ! Register(gameObject.asInstanceOf[Component])
    }
}

    def destroy(gameObject: GameObject) {
        // remove game object
        gameObjects alter (_.filterNot(_.equals(gameObject)))

        componentManagers.foreach { componentManager =>
       		componentManager ! Unregister(gameObject.asInstanceOf[Component])
		}
    }

    def process {
        // Wait for the rendering to finish, otherwise the processing draw thread and the rendering
        // manager's thread will be too much out of sync and rendering will be full of artifacts.
        (renderingManager ? Process).as[String]

        otherComponentManagers.foreach(_ ! Process)
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
