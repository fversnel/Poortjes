package org.frankversnel.poortjes.game;

import org.slf4j.scala.Logging
import processing.core.PApplet
import processing.core.PConstants
import processing.opengl._
import java.util.Properties
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._
import org.frankversnel.poortjes.util._
import org.frankversnel.poortjes.game.gameobjects._

class Poortjes extends PApplet with Logging {
	private var screenWidthPx = 640
	private var screenHeightPx = 480
	private val backgroundClr = 0;

	override def setup = {
		smooth

		screenWidthPx = GameConfiguration.getProperty("screen_width").toInt
		screenHeightPx = GameConfiguration.getProperty("screen_height").toInt
		if(GameConfiguration.isEnabled("opengl")) {
			size(screenWidthPx, screenHeightPx, PConstants.OPENGL)
			hint(PConstants.DISABLE_OPENGL_2X_SMOOTH)
			hint(PConstants.ENABLE_OPENGL_2X_SMOOTH)
		} else {
			size(screenWidthPx, screenHeightPx)
		}

		initialize
	}

	def initialize {
		getKeyListeners.foreach(removeKeyListener(_))

		val resourceLoader = new ProcessingShapeLoader(this)
		// Preload resources
		resourceLoader.addResource("shepherd.svg")
		resourceLoader.addResource("gate-end.svg")
		resourceLoader.addResource("gate-connector.svg")
		resourceLoader.addResource("candy.svg")

		val renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
		EntityManager.initialize(List(
				new RenderingManager(renderer),
				new ComponentProcessingManager,
				new FastCollisionManager {
					type A = GateConnector
					type B = Player

					protected def isCorrectLhs(c: Component) = c.isInstanceOf[GateConnector]
					protected def isCorrectRhs(c: Component) = c.isInstanceOf[Player]
				},
				new FastCollisionManager {
					type A = GateEnd
					type B = Player

					protected def isCorrectLhs(c: Component) = c.isInstanceOf[GateEnd]
					protected def isCorrectRhs(c: Component) = c.isInstanceOf[Player]
				},
				new FastCollisionManager {
					type A = Shepherd
					type B = Player

					protected def isCorrectLhs(c: Component) = c.isInstanceOf[Shepherd]
					protected def isCorrectRhs(c: Component) = c.isInstanceOf[Player]
				},
				new FastCollisionManager {
					type A = Player
					type B = Candy

					protected def isCorrectLhs(c: Component) = c.isInstanceOf[Player]
					protected def isCorrectRhs(c: Component) = c.isInstanceOf[Candy]
				},
				new FastCollisionManager {
					type A = Explosion
					type B = Shepherd

					protected def isCorrectLhs(c: Component) = c.isInstanceOf[Explosion]
					protected def isCorrectRhs(c: Component) = c.isInstanceOf[Shepherd]
				}
			)
		)

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

		addKeyListener(new KeyListener {
			override def keyPressed(event: KeyEvent) {
				if(event.getKeyCode == KeyEvent.VK_ESCAPE) {
					System.exit(0)
				}
			}
			override def keyReleased(event: KeyEvent) {}
			override def keyTyped(event: KeyEvent) {}
		})
		addKeyListener(new KeyListener {
			override def keyPressed(event: KeyEvent) {
				if(event.getKeyCode == KeyEvent.VK_SPACE) {
					initialize
				}
			}
			override def keyReleased(event: KeyEvent) {}
			override def keyTyped(event: KeyEvent) {}
		})
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
