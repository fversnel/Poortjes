package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.moving.Moveable
import org.frankversnel.poortjes.input._

import org.frankversnel.poortjes._

class Player(var color: Color, var transform: Transform, var speed: Speed,
		val mapping: KeyboardMapping)
        extends GameObject with Drawable with Collidable with Moveable with Keyboard {

	override def draw(renderer: Renderer) {
		renderer.drawRectangle(color, transform)
	}
}
object Player {
	def apply(color: Color, transform: Transform, speed: Speed) = {
		new Player(color, transform, speed, KeyboardMapping(' ',' ',' ',' '))
	}
}

