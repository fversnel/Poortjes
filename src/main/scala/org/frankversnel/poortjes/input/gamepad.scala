package org.frankversnel.poortjes.input

import org.lwjgl.input._
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.moving.Speed;

trait Gamepad extends Component with Logging {
	speed: Speed =>

	private val input = Gamepad.controllers(3)
	input.setDeadZone(1, 0.2f)
	input.setDeadZone(2, 0.2f)

	override def process {
		super.process

		// Retrieve new input from the gamepad
		input.poll
		logger.info("x: " + input.getXAxisValue() + " y: " + input.getYAxisValue())
		speed.moveSpeed(input.getYAxisValue * -1)
		speed.rotationSpeed(input.getXAxisValue)
	}
}
object Gamepad {
	private lazy val controllers = {
		Controllers.create
		for(i <- 0 until Controllers.getControllerCount)
				yield Controllers.getController(i)
	}
}
