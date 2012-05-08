package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

class RenderingManager(private val renderer: Renderer) extends ComponentManager {
	type T = Drawable

	override def processComponents(update: Update) = {
		renderer.clearScreen
		super.processComponents(update)
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]

	protected def processComponent(drawable: T, update: Update) = drawable.draw(renderer)
}
