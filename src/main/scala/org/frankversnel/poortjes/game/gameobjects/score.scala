package org.frankversnel.poortjes.game.gameobjects

import org.slf4j.scala.Logging

import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.DimensionValue
import org.frankversnel.poortjes.ColorValue
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.rendering.Renderer

class Score extends Component with Drawable with Logging {
	private var _multiplier = 1
	private var _value = 0

	def multiplier = _multiplier
	def value = _value

	val color = ColorValue().r(255).g(255).b(255)
	var dimension = DimensionValue()

	def incrementMultiplier = {
		_multiplier += 1
		//logger.info("Multiplier: " + _multiplier)
	}
	def incrementScore = {
		_value += _multiplier
		//logger.info("score: " + _value)
	}

	override def draw(renderer: Renderer) {
		renderer.drawText(_value.toString, color, 10, 20)
		renderer.drawText("x" + _multiplier, color, 10, 30)
	}
}
