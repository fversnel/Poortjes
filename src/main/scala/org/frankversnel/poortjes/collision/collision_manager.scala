package org.frankversnel.poortjes.collision

import org.frankversnel.poortjes._

class SimpleCollisionManager extends ComponentManager {
	type T = Collidable

	def processComponents(update: Update) {
		for(lhsCollidable <- allComponents;
			rhsCollidable <- allComponents.filterNot(_ equals lhsCollidable)
			if lhsCollidable.collidesWith(rhsCollidable)
		) yield lhsCollidable.onCollision(rhsCollidable, update)
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Collidable]
}
