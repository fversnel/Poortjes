package org.frankversnel.poortjes.input.keyboard

import org.slf4j.scala.Logging;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

trait JavaKeyListenerAdapter extends KeyListener with Logging {
	keyboard: Keyboard =>

	override def keyPressed(event: KeyEvent) {
		//logger.info("receiving key pressed event: " + event.getKeyChar)

		keyboard.keyPressed(event.getKeyChar)
	}

	override def keyReleased(event: KeyEvent) {
		//logger.info("receiving key released event: " + event.getKeyChar)

		keyboard.keyReleased(event.getKeyChar)
	}

	override def keyTyped(event: KeyEvent) {
		// We don't care about what happens when this event is triggered,
		// we don't need it to move our objects.
	}
}
