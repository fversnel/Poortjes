package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.EntityManager
import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.moving._

abstract class Player(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with Collidable with Moveable with Speed {
	//speed
	val distanceInMs = 0.15f
	val rotationInMs = 0.007f

	protected val shape = resourceLoader.addResource("ship-green.svg")

	var dimension = (9, 13)

	def onCollision(collider: GameObject) {
		if(collider.is[Candy]) {
			collider.destroy
		}
	}
}
