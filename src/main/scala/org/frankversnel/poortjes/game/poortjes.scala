package org.frankversnel.poortjes.game;

import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._;
import org.frankversnel.poortjes.input._;
import org.frankversnel.poortjes.resource_loading._;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	private var newPlayer: Player = null
	private var playerTwo: Player = null
	private var gate: Gate = null

	private var renderer: Renderer = null
	private var resourceLoader: ProcessingShapeLoader = null

	override def setup = {
		logger.info("initializing Poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)

		resourceLoader = new ProcessingShapeLoader(this)
		renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
        EntityManager.initialize(renderer)

		newPlayer = new Player(resourceLoader) {
			//speed
			val distanceInMs = 3f
			val rotationInMs = 0.10f

			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(screenWithPx / 3, screenHeightPx / 3)
		addKeyListener(newPlayer)
		EntityManager().spawn(newPlayer)

        playerTwo = new Player(resourceLoader) {
			//speed
			val distanceInMs = 3f
			val rotationInMs = 0.10f

			val keybindings = KeyboardBindings('i', 'k', 'j', 'l')
        }
		addKeyListener(playerTwo)
		EntityManager().spawn(playerTwo)

        val newGate = Gate.build(resourceLoader)
        newGate.foreach(EntityManager().spawn(_))
	}

	override def draw = {
		newPlayer.move
		playerTwo.move

        EntityManager().process
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
