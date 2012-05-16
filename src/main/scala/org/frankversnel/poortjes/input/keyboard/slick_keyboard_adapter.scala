package org.frankversnel.poortjes.input.keyboard

import org.slf4j.scala.Logging;
import org.newdawn.slick.KeyListener
import org.newdawn.slick.Input

import org.frankversnel.poortjes.moving.Speed;

trait SlickKeyListenerAdapter extends KeyListener with Logging {
	keyboard: Keyboard =>

	def keyPressed(key: Int, char: Char) {
		logger.info("Slick key pressed: " + char)
		keyboard.keyPressed(char)
	}
	def keyReleased(key: Int, char: Char) {
		keyboard.keyReleased(char)
	}

	def setInput(input: Input) {}
	def isAcceptingInput = true
	def inputEnded {}
	def inputStarted {}

}
