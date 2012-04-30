package org.frankversnel.poortjes.input

import scala.math._
import org.lwjgl.input._
import org.newdawn.slick.geom.Vector2f
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.moving.Speed;

trait Gamepad extends Component with Logging {
	self: Speed with Transform =>

	private val input = Gamepad.controllers(3)
	input.setDeadZone(1, 0.3f)
	input.setDeadZone(2, 0.3f)

	var previousVector = new Vector2f(0f, 0.001f)

	override def process {
		super.process

		// Retrieve new input from the gamepad
		input.poll
		//logger.info("x: " + input.getXAxisValue() + " y: " + input.getYAxisValue())
		val inputVector = new Vector2f(input.getXAxisValue, input.getYAxisValue)
		val directionVector = previousVector.sub(inputVector)
		previousVector = inputVector
		//val speed = direction.length
		val rotation = atan2(directionVector.getY, directionVector.getX)
		//logger.info("rotation: " + rotation)
		//self.rotationSpeed = inputVector.getX
		self.moveSpeed = (inputVector.getX, inputVector.getY * -1)
	}
}
object Gamepad {
	private lazy val controllers = {
		Controllers.create
		for(i <- 0 until Controllers.getControllerCount)
				yield Controllers.getController(i)
	}
}
