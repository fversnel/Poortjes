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

	private val Deadzone = 0.3f

	protected val input: Controller

	override def process(update: Update) {
		super.process(update)
		input.setDeadZone(1, Deadzone)
		input.setDeadZone(2, Deadzone)

		// Retrieve new input from the gamepad
		input.poll

		//logger.info("x: " + input.getXAxisValue + " y: " + input.getYAxisValue)
		val direction = new Vector2f(input.getXAxisValue, input.getYAxisValue)

		//logger.info("input vector length: " + direction.length)
		if(direction.length > 0.2f) {
			val rotation = atan2(direction.getX, -direction.getY).toFloat
			//logger.info("rotation: " + rotation)
			self.setToRotation(rotation)
		}

		val speed = direction.length
		self.moveSpeed = (0, speed)
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
