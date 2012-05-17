package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.resource_loading.ResourceId
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.util.GameConfiguration

abstract class Player(val resourceLoader: ResourceLoader) extends DrawableShape
		with CollidableRectangle with Moveable with Speed {
	//speed
	val distanceInMs = GameConfiguration.getProperty("player_move_speed").toFloat
	val rotationInMs = GameConfiguration.getProperty("player_rotation_speed").toFloat

	var dimension = DimensionValue().width(9).height(13)

	val shape: ResourceId

	def onCollision(collider: Collidable, update: Update) {
		if(collider.isInstanceOf[Candy]) {
			val score = update.gameObjects.filter(_.isInstanceOf[Score]).headOption
			if(score.isDefined) {
				score.get.asInstanceOf[Score].incrementMultiplier
			}

			collider.destroy
		}
	}
}
