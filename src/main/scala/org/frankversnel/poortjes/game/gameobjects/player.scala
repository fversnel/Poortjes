package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.EntityManager
import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.DimensionValue
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.resource_loading.ResourceId
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.moving._

abstract class Player(val resourceLoader: ResourceLoader) extends DrawableShape
		with Collidable with Moveable with Speed {
	//speed
	val distanceInMs = 0.15f
	val rotationInMs = 0.007f

	var dimension = DimensionValue().width(9).height(13)

	val shape: ResourceId

	def onCollision(candy: GameObject) {
		//val scores = EntityManager().gameObjects.map(_.as[Score]).flatten
		//if(scores.nonEmpty) {
		//	scores.head.incrementMultiplier
		//}

		candy.destroy
	}
}
