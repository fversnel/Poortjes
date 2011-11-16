package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._

import org.frankversnel.poortjes._

abstract class Player(val ship: ResourceId)
		extends GameObject with Drawable with Collidable with Moveable 
		with Keyboard with Speed {

	override def draw(renderer: Renderer) {
		renderer.drawShape(ship, this)
	}
}
abstract class StillObject extends Drawable with Collidable with Color {

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this)
	}
}
