package org.frankversnel.poortjes.collision

import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Circle

import org.frankversnel.poortjes.Transform

trait CollidableCircle extends Collidable {

	override protected def boundingBox: Shape = {
		val boundingBox = new Circle(0f, 0f, dimension.width / 2)
		boundingBox.transform(Transform.slickMatrix(this))
	}
}
