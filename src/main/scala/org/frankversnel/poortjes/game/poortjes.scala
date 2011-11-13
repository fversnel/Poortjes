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
import org.frankversnel.poortjes.resource_loading.ProcessingShapeLoader;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	private var newPlayer: Player = null

	private var renderer: Renderer = null
	private var renderingManager: ActorRef = null
	private var collisionManager: ActorRef = null

	override def setup = {
		logger.info("initializing Poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)
		smooth

		renderer = new Processing2DRenderer(g, new ProcessingShapeLoader(this), backgroundClr)
		renderingManager = actorOf(new RenderingManager(renderer)).start
		collisionManager = actorOf[CollisionManager].start

		val playerTransform = new Transform(Dimension(50, 50))
		playerTransform.translate(screenWithPx / 3, screenHeightPx / 3)
		newPlayer = new Player(Color.red, playerTransform, new Speed(1f, 0.05f),
				KeyboardMapping('w', 's', 'a', 'd'))
		addKeyListener(newPlayer)
		renderingManager ! Register(newPlayer)
		collisionManager ! Register(newPlayer)

		val anotherPlayerTransform = new Transform(Dimension(50, 50))
		anotherPlayerTransform.translate(screenWithPx / 2, screenHeightPx / 2)
		val anotherPlayer = Player(Color.green, anotherPlayerTransform, new Speed(0.01f, 0.1f))
		renderingManager ! Register(anotherPlayer)
		collisionManager ! Register(anotherPlayer)
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
