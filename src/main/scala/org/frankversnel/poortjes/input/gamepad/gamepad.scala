package org.frankversnel.poortjes.input.gamepad

import scala.math._
import org.lwjgl.input._
import org.newdawn.slick.geom.Vector2f
import org.slf4j.scala.Logging

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.moving.Speed
import org.frankversnel.poortjes.util.DeltaTime

trait Gamepad extends Component with Logging {
	self: Speed with Transform =>

	protected val input: Controller

	override def process(update: Update) {
		super.process(update)

		// Retrieve new input from the gamepad
		input.poll

		//logger.info("x: " + input.getXAxisValue() + " y: " + input.getYAxisValue())
		val inputVector = new Vector2f(input.getXAxisValue, input.getYAxisValue)

		//val speed = direction.length
		val rotation = atan2(inputVector.getY, inputVector.getX)
		//logger.info("rotation: " + rotation)
		//self.setToRotation(rotation.toFloat)

		self.moveSpeed = (inputVector.getX, -inputVector.getY)
	}
}
object Gamepad {
	lazy val controllers = {
		Controllers.create
		for(i <- 0 until Controllers.getControllerCount)
				yield Controllers.getController(i)
	}

	def isButtonPressed(button: Button): Option[Controller] = {
		controllers.find { c =>
			c.poll
			c.isButtonPressed(button.index)
		}
	}
}
