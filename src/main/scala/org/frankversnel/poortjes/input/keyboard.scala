package org.frankversnel.poortjes.input

import org.slf4j.scala.Logging;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.moving.Speed;

trait Keyboard extends Component with KeyListener with Logging {
	speed: Speed =>

	private val FullSpeed = 1
	private val NoSpeed = 0

	val keybindings: KeyboardBindings

	override def keyPressed(event: KeyEvent) {
		//logger.info("receiving key pressed event: " + event.getKeyChar)

		keyPressed(event.getKeyChar)
	}
	protected def keyPressed(char: Char) {
		setSpeed(char, FullSpeed)
	}

	override def keyReleased(event: KeyEvent) {
		//logger.info("receiving key released event: " + event.getKeyChar)

		keyReleased(event.getKeyChar)
	}
	protected def keyReleased(char: Char) {
		setSpeed(char, NoSpeed)
	}

	override def keyTyped(event: KeyEvent) {
		// We don't care about what happens when this event is triggered,
		// we don't need it to move our objects.
	}

	private def setSpeed(keyPressed: Char, speedAmount: Int) = {
		keyPressed match {
			case keybindings.keyForward => speed.moveSpeed(speedAmount)
			case keybindings.keyBackward => speed.moveSpeed(-speedAmount)
			case keybindings.keyLeft => speed.rotationSpeed(-speedAmount)
			case keybindings.keyRight => speed.rotationSpeed(speedAmount)
			case _ => // Do nothing
		}
	}
}

case class KeyboardBindings(val keyForward: Char, val keyBackward: Char,
		val keyLeft: Char, val keyRight: Char)
