package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.moving.Moveable

import org.frankversnel.poortjes._

class Player(var color: Color, var transform: Transform)
        extends GameObject with Drawable with Collidable with Moveable {

	override def draw(renderer: Renderer) {
		renderer.drawCircle(color, transform)
	}
}
