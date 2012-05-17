package org.frankversnel.poortjes.collision

import org.newdawn.slick.geom.Shape
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Collidable extends Transform with Dimension with Logging {

	def collidesWith(otherCollidable: Collidable): Boolean = {
		boundingBox.intersects(otherCollidable.boundingBox)
	}

	def onCollision(collider: Collidable, update: Update): Unit

	protected def boundingBox: Shape
}
