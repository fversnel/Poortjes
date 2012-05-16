package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.DimensionValue
import org.frankversnel.poortjes.util.GameConfiguration

class Candy(protected val resourceLoader: ResourceLoader) extends DrawableShape with Collidable
		with TimeBasedLife {
	protected val maxTimeAliveMillis = 
			GameConfiguration.getProperty("candy_life_time_in_seconds").toLong * 1000L

	protected val shape = resourceLoader.addResource("candy.svg")

	var dimension = DimensionValue().width(5).height(5)

	def onCollision(collider: Collidable, update: Update) {
		// Do nothing
	}
}
