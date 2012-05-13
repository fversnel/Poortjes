package org.frankversnel.poortjes.game;

import org.slf4j.scala.Logging;
import processing.core.PApplet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._
import org.frankversnel.poortjes.util._
import org.frankversnel.poortjes.game.gameobjects._

class Poortjes extends PApplet with Logging {
	private val screenWidthPx = 800;
	private val screenHeightPx = 500;
	private val backgroundClr = 0;

	override def setup = {
		size(screenWidthPx, screenHeightPx)
		background(backgroundClr)
		smooth

		addKeyListener(new KeyListener {
			override def keyPressed(event: KeyEvent) {
				if(event.getKeyCode == KeyEvent.VK_SPACE) {
					setup
				}
			}
			override def keyReleased(event: KeyEvent) {}
			override def keyTyped(event: KeyEvent) {}
		})

		val resourceLoader = new ProcessingShapeLoader(this)
		val renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
		EntityManager.initialize(renderer)

		val newPlayer = new Player(resourceLoader) with Keyboard {
			val shape = resourceLoader.addResource("ship-green.svg")
			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate((screenWidthPx / 2) - 10, screenHeightPx / 2)
		addKeyListener(newPlayer)
		EntityManager().spawn(newPlayer)

		val playerTwo = new Player(resourceLoader) with Keyboard {
			val shape = resourceLoader.addResource("ship-purple.svg")
			val keybindings = KeyboardBindings('i', 'k', 'j', 'l')
		}
		playerTwo.translate((screenWidthPx / 2) + 10, screenHeightPx / 2)
		addKeyListener(playerTwo)
		EntityManager().spawn(playerTwo)

		val spawnArea = new SpawnArea(EntityManager(), Area(0, screenWidthPx, 0,
				screenHeightPx))
		val gateSpawner = new GateSpawner(spawnArea, resourceLoader)
		EntityManager().spawn(gateSpawner)

		val shepherdSpawner = new ShepherSpawner(spawnArea, resourceLoader)
		EntityManager().spawn(shepherdSpawner)

		EntityManager().spawn(new Score)
	}

	var oldTime = 0L
	override def draw = {
		val newTime = millis
		val deltaMillis = (newTime - oldTime).toInt
		oldTime = newTime

		EntityManager().process(DeltaTime(deltaMillis))
		EntityManager().cleanUp
	}
}

object Poortjes extends App with Logging {
	logger.info("initializing Poortjes...")
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
