package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging
import processing.core.PGraphics

import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.Position

class Processing2DRenderer(val graphics: PGraphics) extends Renderer with Logging {
	def drawCircle(color : Color, position : Position) {
		logger.info("rendering color r: %d, g: %d, b: %d on position x: %f, y: %f"
			.format( color.r, color.g, color.b, position.x, position.y))

    fill(color)
    graphics.ellipse(position.x, position.y, 50, 50)
	}

  private def fill(c: Color) = graphics.fill(c.r, c.g, c.b)
}
