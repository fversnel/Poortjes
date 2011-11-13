package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

trait Drawable {
	var color: Color
	var transform: Transform

	def draw(renderer: Renderer): Unit
}
