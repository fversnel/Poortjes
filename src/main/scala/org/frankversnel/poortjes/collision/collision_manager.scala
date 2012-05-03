package org.frankversnel.poortjes.collision

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.util.DeltaTime

class CollisionManager extends ComponentManager {
	type T = Collidable

	protected def processComponent(collidable: T, deltaTime: DeltaTime) {
		val otherComponents = allComponents.filterNot(_ equals collidable)

		otherComponents.foreach { otherCollidable =>
			collidable collidesWith otherCollidable
		}
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Collidable]
}
