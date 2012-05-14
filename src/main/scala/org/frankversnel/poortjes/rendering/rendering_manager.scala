package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

class RenderingManager(private val renderer: Renderer) extends ComponentManager {
	type T = Drawable

	def processComponents(update: Update) {
		renderer.clearScreen
		allComponents.foreach(_.draw(renderer))
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]
}
