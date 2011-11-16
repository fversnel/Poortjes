package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.input._

import org.frankversnel.poortjes._

abstract class Player(val shapeId: String)
		extends GameObject with Drawable with Collidable with Moveable 
		with Keyboard with Speed with Transform {

	override def draw(renderer: Renderer) {
		renderer.drawShape(shapeId, this)
	}
}
