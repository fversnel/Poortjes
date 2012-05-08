package org.frankversnel.poortjes.game

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.game.gameobjects.Player

class GameStateMachine extends Component with Logging {
	case class GameState
	case object Playing extends GameState
	case object GameOver extends GameState

	private var currentState: GameState = Playing

	override def process(update: Update) {
		currentState match {
			case Playing => 
				val noPlayersLeft = update.gameObjects.filter(_.is[Player]).isEmpty
				if(noPlayersLeft) {
					currentState = GameOver
				}
			case GameOver =>
				logger.info("game over")
				currentState = Playing
				// Wait for start button to be pressed
		}

	}
}
