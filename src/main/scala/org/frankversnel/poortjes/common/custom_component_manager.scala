package org.frankversnel.poortjes

class ComponentProcessingManager extends ComponentManager {
	type T = Component

	def processComponents(update: Update) {
		allComponents.foreach(_.process(update))
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Component]
}
