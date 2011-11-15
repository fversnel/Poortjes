package org.frankversnel.poortjes.game;

import akka.actor.Actor._
import akka.actor.ActorRef
import akka.util.duration._
import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.ComponentManager._;
import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._;
import org.frankversnel.poortjes.input._;
import org.frankversnel.poortjes.resource_loading._;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	private var newPlayer: Player = null

	private var renderer: Renderer = null
	private var renderingManager: ActorRef = null
	private var collisionManager: ActorRef = null
	private var resourceLoader: ProcessingShapeLoader = null

	override def setup = {
		logger.info("initializing Poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)
		smooth

		resourceLoader = new ProcessingShapeLoader(this)

		renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
		renderingManager = actorOf(new RenderingManager(renderer)).start

		collisionManager = actorOf[CollisionManager].start

		newPlayer = new Player(resourceLoader.addResource("ship.svg")) {
			var transform = new Transform(Dimension(17, 25))
			transform.translate(screenWithPx / 3, screenHeightPx / 3)
			var speed = new Speed(7f, 0.10f)
			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		addKeyListener(newPlayer)
		renderingManager ! Register(newPlayer)
		collisionManager ! Register(newPlayer)

		val stillObject = new StillObject(Color.green) {
			var transform = new Transform(Dimension(25, 25))
			transform.translate(screenWithPx / 2, screenHeightPx / 2)
		}
		renderingManager ! Register(stillObject)
		collisionManager ! Register(stillObject)
	}

	override def draw = {
		newPlayer.move

		// TODO The timeout is a dirty hack until we find out how to generate a valid return value
		// for the actor message Process
		val result = (renderingManager ? Process)(timeout = 12 millis).as[Unit]

		collisionManager ! Process
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
