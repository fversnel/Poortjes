package org.frankversnel.poortjes.collision

import org.frankversnel.poortjes._

class CollisionManager extends ComponentManager {
	type T = Collidable

	protected def processComponent(collidable: T, update: Update) {
		val otherComponents = allComponents.filterNot(_ equals collidable)

		otherComponents.foreach { otherCollidable =>
			if(collidable collidesWith otherCollidable) {
				collidable.onCollision(otherCollidable)
			}
		}
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Collidable]
}
