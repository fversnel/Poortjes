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
import org.frankversnel.poortjes.input.keyboard._
import org.frankversnel.poortjes.input.gamepad._
import org.frankversnel.poortjes.resource_loading._
import org.frankversnel.poortjes.util._
import org.frankversnel.poortjes.game.gameobjects._

class Poortjes extends PApplet with Logging {
	private var screenWidthPx = 640
	private var screenHeightPx = 480
	private val backgroundClr = 0;

	override def setup = {
		screenWidthPx = GameConfiguration.getProperty("screen_width").toInt
		screenHeightPx = GameConfiguration.getProperty("screen_height").toInt
		if(GameConfiguration.isEnabled("opengl")) {
			size(screenWidthPx, screenHeightPx, PConstants.OPENGL)
			smooth
			hint(PConstants.DISABLE_OPENGL_2X_SMOOTH)
			hint(PConstants.ENABLE_OPENGL_2X_SMOOTH)
		} else {
			size(screenWidthPx, screenHeightPx)
			smooth
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
		val gameWidth = GameConfiguration.getProperty("game_width").toInt
		val gameHeight = GameConfiguration.getProperty("game_height").toInt
		val worldMatrix = new Transform {
			var dimension = DimensionValue().width(gameWidth).height(gameHeight)
			setToScale(screenWidthPx.toFloat / gameWidth.toFloat,
					screenHeightPx.toFloat / gameHeight.toFloat)
		}
		EntityManager.initialize(List(
				new RenderingManager(renderer, worldMatrix),
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

		if(GameConfiguration.isEnabled("keyboard")) {
			val newPlayer = new Player(resourceLoader) with Keyboard with JavaKeyListenerAdapter {
				val shape = resourceLoader.addResource("ship-green.svg")
				val keybindings = KeyboardBindings('w', 's', 'a', 'd')
			}
			newPlayer.translate((gameWidth / 2) - 10, gameHeight / 2)
			addKeyListener(newPlayer)
			EntityManager().spawn(newPlayer)

			val playerTwo = new Player(resourceLoader) with Keyboard with JavaKeyListenerAdapter {
				val shape = resourceLoader.addResource("ship-purple.svg")
				val keybindings = KeyboardBindings('i', 'k', 'j', 'l')
			}
			playerTwo.translate((gameWidth / 2) + 10, gameHeight / 2)
			addKeyListener(playerTwo)
			EntityManager().spawn(playerTwo)
		}

		val playerSpawner = new PlayerSpawner(resourceLoader)
		EntityManager().spawn(playerSpawner)

		val spawnArea = new SpawnArea(EntityManager(), Area(0, gameWidth, 0,
				gameHeight))
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

	override def draw = {
		//Gamepad.controllers.foreach { c =>
		//	c.poll
		//	for(i <- 0 until c.getButtonCount) {
		//		if(c.isButtonPressed(i)) {
		//			logger.info("Pressed button: " + i)
		//		}
		//	}
		//}
		EntityManager().process
		EntityManager().cleanUp
	}
}

object Poortjes extends App with Logging {
	logger.info("initializing Poortjes...")

	if(GameConfiguration.isEnabled("full_screen")) {
		PApplet.main(Array("--present", "org.frankversnel.poortjes.game.Poortjes"))
	} else {
		PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"))
	}
}
