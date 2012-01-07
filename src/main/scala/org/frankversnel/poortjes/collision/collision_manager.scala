package org.frankversnel.poortjes.collision

import org.frankversnel.poortjes._

class CollisionManager extends ComponentManager {
    type T = Collidable

	protected def processComponent(collidable: T) {
		val otherComponents = allComponents.filterNot(_ equals collidable)

		otherComponents.foreach { otherCollidable =>
			collidable checkCollision otherCollidable
		}
	}
}
