package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.DimensionValue

class Candy(protected val resourceLoader: ResourceLoader) extends DrawableShape with Collidable
		with TimeBasedLife {
	protected val maxTimeAliveMillis = 10000

	protected val shape = resourceLoader.addResource("candy.svg")

	var dimension = DimensionValue().width(5).height(5)

	def onCollision(collider: GameObject) {
		// Do nothing
	}
}
