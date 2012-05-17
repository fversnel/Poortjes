package org.frankversnel.poortjes.game.gameobjects

import org.lwjgl.input.Controller

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.input.gamepad._
import org.frankversnel.poortjes.resource_loading.ResourceId
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.util.GameConfiguration

class PlayerSpawner(resourceLoader: ResourceLoader) extends Component {

	private var gamepadsTaken: List[Controller] = Nil
	private var numberOfPlayers = 0

	override def process(update: Update) {
		super.process(update)

		val gamepad = Gamepad.isButtonPressed(BackButton)
		if(gamepad.isDefined && !gamepadsTaken.contains(gamepad.get)) {
			val newPlayer = new Player(resourceLoader) with Gamepad {
				protected val input = gamepad.get
				input.setDeadZone(1, 0.3f)
				input.setDeadZone(2, 0.3f)
				val shape = resourceLoader.addResource("ship-red.svg")
			}
			newPlayer.translate(GameConfiguration.getProperty("game_width").toInt / 2,
								GameConfiguration.getProperty("game_height").toInt / 2)

			EntityManager().spawn(newPlayer)
			gamepadsTaken = gamepad.get :: gamepadsTaken
			numberOfPlayers += 1
		}
	}
}
