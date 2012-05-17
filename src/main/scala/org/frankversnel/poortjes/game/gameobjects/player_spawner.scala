package org.frankversnel.poortjes.game.gameobjects

import org.lwjgl.input.Controller

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.input.gamepad._
import org.frankversnel.poortjes.resource_loading.ResourceId
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.util.GameConfiguration

class PlayerSpawner(resourceLoader: ResourceLoader) extends Component {
	private val GameWidth = GameConfiguration.getProperty("game_width").toInt
	private val GameHeight = GameConfiguration.getProperty("game_height").toInt

	private var availablePlayerIcons = List(
			resourceLoader.addResource("ship-red.svg"),
			resourceLoader.addResource("ship-green.svg"),
			resourceLoader.addResource("ship-purple.svg"),
			resourceLoader.addResource("ship-blue.svg"))
	private var spawnLocations = List(
			((GameWidth / 2) - 10,	(GameHeight / 2) - 10),
			((GameWidth / 2) + 10,	(GameHeight / 2) - 10),
			((GameWidth / 2) - 10,	(GameHeight / 2) + 10),
			((GameWidth / 2) + 10,	(GameHeight / 2) + 10))
	private var gamepadsTaken: List[Int] = Nil
	private var numberOfPlayers = 0

	override def process(update: Update) {
		super.process(update)

		if(GameConfiguration.isEnabled("gamepad")) {
			val gamepad = Gamepad.isButtonPressed(BackButton)
			if(gamepad.isDefined && !gamepadsTaken.contains(gamepad.get.getIndex) && numberOfPlayers < 4) {
				gamepadsTaken = gamepad.get.getIndex :: gamepadsTaken
				numberOfPlayers += 1

				val newPlayer = createPlayer(gamepad.get)
				EntityManager().spawn(newPlayer)
			}
		}
	}

	private def createPlayer(gamepad: Controller) = {
		val newPlayer = new Player(resourceLoader) with Gamepad {

			protected val input = gamepad
			val shape = availablePlayerIcons.head
			availablePlayerIcons = availablePlayerIcons.tail
		}
		val newPlayerLocation = spawnLocations.head
		spawnLocations = spawnLocations.tail
		newPlayer.translate(newPlayerLocation._1, newPlayerLocation._2)
		newPlayer
	}
}
