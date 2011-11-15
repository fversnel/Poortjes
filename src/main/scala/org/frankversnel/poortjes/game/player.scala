package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.moving.Moveable
import org.frankversnel.poortjes.input._

import org.frankversnel.poortjes._

abstract class Player(val shapeId: String)
		extends GameObject with Drawable with Collidable with Moveable with Keyboard {

	override def draw(renderer: Renderer) {
		renderer.drawShape(shapeId, transform)
	}
}
