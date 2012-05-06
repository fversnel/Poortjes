package org.frankversnel.poortjes.collision

import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Rectangle
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Collidable extends Transform with Dimension with Logging {

	def collidesWith(otherCollidable: Collidable): Boolean = {
		val ourBoundingBox = createBoundingBox(this)
		val theirBoundingBox = createBoundingBox(otherCollidable)

		return ourBoundingBox.intersects(theirBoundingBox)
	}

	def onCollision(collider: GameObject): Unit

	private def createBoundingBox(collidable: Collidable): Shape = {
		val boundingBox = new Rectangle(0, 0, collidable.width, collidable.height)
		boundingBox.transform(Transform.slickMatrix(collidable))
	}
}
