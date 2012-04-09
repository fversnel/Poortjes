package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

trait Drawable extends Transform {

	def draw(renderer: Renderer): Unit
}
