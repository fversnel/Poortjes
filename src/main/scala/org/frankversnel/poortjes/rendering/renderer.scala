package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.Position

trait Renderer {
	def drawCircle(color : Color, position : Position)
}
