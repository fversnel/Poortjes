package org.frankversnel.poortjes.game;

import akka.actor.Actor._
import akka.actor.ActorRef
import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.ComponentManager._;
import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.resource_loading.ProcessingShapeLoader;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	private var newPlayer: Player = null

	private var renderingManager: ActorRef = null

	override def setup = {
		logger.info("initializing Poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)
		smooth

		val renderer = new Processing2DRenderer(g, new ProcessingShapeLoader(this), backgroundClr)
		renderingManager = actorOf(new RenderingManager(renderer)).start

		newPlayer = new Player(Color.red, new Transform(Dimension(50, 50)))
		renderingManager ! Register(newPlayer)
	}

	override def draw = {
		newPlayer.move
		renderingManager ! Process
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
