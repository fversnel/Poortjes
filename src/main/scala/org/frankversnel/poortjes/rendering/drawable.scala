package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.Position

trait Drawable {
	var color: Color
	var position: Position

	def draw(renderer: Renderer): Unit
}
