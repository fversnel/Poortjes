package org.frankversnel.poortjes.game.gameobjects

import org.slf4j.scala.Logging

import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.DimensionValue
import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.rendering.Renderer

class Score extends Drawable with Logging {
	private var _multiplier = 1L
	private var _value = 0L

	private val textColor = Color.white
	var dimension = DimensionValue()

	translate(0, 10)

	def incrementMultiplier = {
		_multiplier += 1L
		//logger.info("Multiplier: " + _multiplier)
	}
	def incrementScore = {
		_value += _multiplier
		//logger.info("score: " + _value)
	}

	override def draw(renderer: Renderer) {
		renderer.drawText(this, _value + "\nx" + _multiplier, textColor)
	}
}
