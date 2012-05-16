package org.frankversnel.poortjes.input.keyboard

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.moving.Speed;

trait Keyboard extends Component with Logging {
	speed: Speed =>

	private val FullSpeed = 1
	private val NoSpeed = 0

	val keybindings: KeyboardBindings

	def keyPressed(char: Char) {
		setSpeed(char, FullSpeed)
	}

	def keyReleased(char: Char) {
		setSpeed(char, NoSpeed)
	}

	private def setSpeed(keyPressed: Char, speedAmount: Int) = {
		keyPressed match {
			case keybindings.keyForward => speed.moveSpeed = (0, speedAmount)
			case keybindings.keyBackward => speed.moveSpeed = (0, -speedAmount)
			case keybindings.keyLeft => speed.rotationSpeed = -speedAmount
			case keybindings.keyRight => speed.rotationSpeed = speedAmount
			case _ => // Do nothing
		}
	}
}

case class KeyboardBindings(keyForward: Char, keyBackward: Char,
		keyLeft: Char, keyRight: Char)
