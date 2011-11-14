package org.frankversnel.poortjes.collision

import org.frankversnel.poortjes._

class CollisionManager extends ComponentManager[Collidable] {

	protected def processComponent(collidable: Collidable) {
		val otherComponents = allComponents.filterNot(_ equals collidable)

		otherComponents.foreach { otherCollidable =>
			collidable checkCollision otherCollidable
		}
	}
}
