package org.frankversnel.poortjes.input

import org.slf4j.scala.Logging;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.frankversnel.poortjes._;

trait Keyboard extends Component with KeyListener with Logging {
	val mapping: KeyboardMapping

	val FullSpeed = 1
	val NoSpeed = 0

	var speed: Speed

	override def keyPressed(event: KeyEvent) {
		//logger.info("receiving key pressed event: " + event.getKeyChar)

		setSpeed(event.getKeyChar, FullSpeed)
	}

	override def keyReleased(event: KeyEvent) {
		//logger.info("receiving key released event: " + event.getKeyChar)

		setSpeed(event.getKeyChar, NoSpeed)
	}

	override def keyTyped(event: KeyEvent) {
		// We don't care about what happens when this event is triggered,
		// we don't need it to move our objects.
	}

	private def setSpeed(keyPressed: Char, speedAmount: Int) = {
		keyPressed match {
			case mapping.keyForward => speed.move(speedAmount)
			case mapping.keyBackward => speed.move(-speedAmount)
			case mapping.keyLeft => speed.rotate(-speedAmount)
			case mapping.keyRight => speed.rotate(speedAmount)
			case _ => // Do nothing
		}
	}
}

case class KeyboardMapping(val keyForward: Char, val keyBackward: Char,
	val keyLeft: Char, val keyRight: Char)
