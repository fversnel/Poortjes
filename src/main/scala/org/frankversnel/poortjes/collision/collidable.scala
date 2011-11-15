package org.frankversnel.poortjes.collision

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Collidable extends Component with Logging {
	var transform: Transform

	def checkCollision(otherCollidable: Collidable) {
		val ourBoundingBox = createBoundingBox(transform)
		val theirBoundingBox = createBoundingBox(otherCollidable.transform)

		val collision = ourBoundingBox.intersects(theirBoundingBox.getBounds2D)

		if(collision) {
			logger.info("collision")
		}
	}

	private def createBoundingBox(transform: Transform): Area = {
		val boundingBox = new Area(new Rectangle2D.Float(0, 0, transform.width, transform.height));
		boundingBox.transform(transform.affineTransform);
		return boundingBox;
	}
}