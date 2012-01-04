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
	private var resourceLoader: ProcessingShapeLoader = null
	private var entityManager: EntityManager = null

	override def setup = {
		logger.info("initializing Poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)
		smooth

		resourceLoader = new ProcessingShapeLoader(this)
		renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
		entityManager = new EntityManager(renderer)

		newPlayer = new Player(resourceLoader.addResource("ship.svg")) {
			val dimension = Dimension(17, 25)

			//speed
			val distanceInMs = 7f
			val rotationInMs = 0.10f

			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(screenWithPx / 3, screenHeightPx / 3)
		addKeyListener(newPlayer)
		entityManager.spawn(newPlayer)

		val stillObject = new StillObject {
			val color = ColorValue.green
			val dimension = Dimension(25, 25)
		}
		stillObject.translate(screenWithPx / 2, screenHeightPx / 2)
		entityManager.spawn(stillObject)

        Gate.build(resourceLoader).foreach(entityManager.spawn(_))
	}

	override def draw = {
		newPlayer.move

		entityManager.process
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
