package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

class ComponentProcessingManager extends ComponentManager {
	type T = Component

	protected def isCorrectType(component: Component) = component.isInstanceOf[Component]

	protected def processComponent(component: T, deltaTime: DeltaTime) {
		component.process(deltaTime)
	}
}
