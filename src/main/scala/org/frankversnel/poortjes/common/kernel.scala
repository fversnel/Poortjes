package org.frankversnel.poortjes

import akka.actor.Actor._
import akka.actor.ActorRef
import akka.stm._

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._
import ComponentManager._

class EntityManager (val renderer: Renderer) {

	private val renderingManager = actorOf(new RenderingManager(renderer)).start
	private val collisionManager = actorOf(new CollisionManager).start
	private val componentManagers = List(renderingManager, collisionManager)

	private val gameObjects = Ref(List[GameObject]())

    def spawn(gameObject: GameObject) {
		gameObjects alter (gameObject :: _)

		gameObject.as[Drawable] match {
			case Some(d) => renderingManager ! Register(d)
			case _ =>
		}
		gameObject.as[Collidable] match {
			case Some(c) => collisionManager ! Register(c)
			case _ =>
		}
	}

	def destroy(gameObject: GameObject) {
    }

	def process {
		// Wait for the rendering to finish, otherwise the processing draw thread and the rendering
		// manager's thread will be too much out of sync and rendering will be full of artifacts.
		(renderingManager ? Process).as[String]

		collisionManager ! Process
	}
}
