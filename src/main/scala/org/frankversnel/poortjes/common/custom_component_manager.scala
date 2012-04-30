package org.frankversnel.poortjes

class ComponentProcessingManager extends ComponentManager {
	type T = Component

	protected def isCorrectType(component: Component) = component.isInstanceOf[Component]

	protected def processComponent(component: T) = component.process
}
