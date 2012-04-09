package org.frankversnel.poortjes.input

import org.slf4j.scala.Logging;
import org.newdawn.slick.KeyListener
import org.newdawn.slick.Input

import org.frankversnel.poortjes.moving.Speed;

trait SlickKeyboardAdapter extends Keyboard with KeyListener with Logging {
	speed: Speed =>

	def keyPressed(key: Int, char: Char) {
		logger.info("Slick key pressed: " + char)
		super.keyPressed(char)
	}
	def keyReleased(key: Int, char: Char) {
		super.keyReleased(char)
	}

	def setInput(input: Input) {}
	def isAcceptingInput = true
	def inputEnded {}
	def inputStarted {}

}
