package org.frankversnel.poortjes.collision

import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Rectangle
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Collidable extends Transform with Dimension with Logging {

	def collidesWith(otherCollidable: Collidable): Boolean = {
		boundingBox.intersects(otherCollidable.boundingBox)
	}

	def onCollision(collider: GameObject): Unit

	protected def boundingBox: Shape = {
		val boundingBox = new Rectangle(0, 0, dimension.width, dimension.height)
		boundingBox.transform(Transform.slickMatrix(this))
	}
}
