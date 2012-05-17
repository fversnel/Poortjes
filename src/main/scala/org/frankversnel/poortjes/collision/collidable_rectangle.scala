package org.frankversnel.poortjes.collision

import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Rectangle

import org.frankversnel.poortjes.Transform

trait CollidableRectangle extends Collidable {

	protected def boundingBox: Shape = {
		val boundingBox = new Rectangle(0, 0, dimension.width, dimension.height)
		boundingBox.transform(Transform.slickMatrix(this))
	}
}
