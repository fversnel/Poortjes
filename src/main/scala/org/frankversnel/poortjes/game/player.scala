package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.rendering.Drawable

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.Position

class Player(var color: Color, var position: Position)
        extends GameObject with Drawable {

	override def draw(renderer: Renderer) {
		renderer.drawCircle(color, position)
	}
}
