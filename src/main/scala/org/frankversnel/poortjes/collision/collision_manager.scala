package org.frankversnel.poortjes.collision

import org.frankversnel.poortjes._

class CollisionManager extends ComponentManager with ConcurrentProcessing {
    type T = Collidable

	protected def processComponent(collidable: T) {
		val otherComponents = allComponents.filterNot(_ equals collidable)

		otherComponents.foreach { otherCollidable =>
			collidable collidesWith otherCollidable
		}
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Collidable]
}
