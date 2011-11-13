package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.rendering.Drawable

import org.frankversnel.poortjes._

class Player(var color: Color, var transform: Transform)
        extends GameObject with Drawable {

	override def draw(renderer: Renderer) {
		renderer.drawCircle(color, transform)
	}
}
