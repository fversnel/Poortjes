package org.frankversnel.poortjes.game;

import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._
import org.frankversnel.poortjes.util._
import org.frankversnel.poortjes.game.gameobjects._

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 800;
	private val screenHeightPx = 500;
	private val backgroundClr = 0;

	override def setup = {
		logger.info("initializing Poortjes...")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)

		val resourceLoader = new ProcessingShapeLoader(this)
		val renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
		EntityManager.initialize(renderer)

		val newPlayer = new Player(resourceLoader) with Keyboard {
			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(screenWithPx / 3, screenHeightPx / 3)
		addKeyListener(newPlayer)
		EntityManager().spawn(newPlayer)

		val playerTwo = new Player(resourceLoader) with Keyboard {
			val keybindings = KeyboardBindings('i', 'k', 'j', 'l')
		}
		playerTwo.translate(screenWithPx / 2, screenHeightPx / 2)
		addKeyListener(playerTwo)
		//EntityManager().spawn(playerTwo)

		for (i <- 0 until 5) {
			val enemy = new Shepherd(resourceLoader)
			enemy.translate(i * 20, 50)
			EntityManager().spawn(enemy)
		}

		val candy = new Candy(resourceLoader)
		candy.translate(50, 50)
		EntityManager().spawn(candy)

		val gate = Gate.build(resourceLoader)
		EntityManager().spawn(gate)
		gate.translate(300, 200)

		val newGate = Gate.build(resourceLoader)
		newGate.translate(100, 200)
		EntityManager().spawn(newGate)
	}

	var oldTime = 0
	override def draw = {
		val newTime = millis
		val deltaMillis = (newTime - oldTime)
		oldTime = newTime

		EntityManager().process(DeltaTime(deltaMillis))
		EntityManager().cleanUp
	}
}

object Poortjes extends App with Logging {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
