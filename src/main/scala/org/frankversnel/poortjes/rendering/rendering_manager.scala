package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

class RenderingManager(private val renderer: Renderer) extends ComponentManager {
    type T = Drawable

	protected override def processComponents = {
		renderer.clearScreen
		super.processComponents
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]

	protected def processComponent(drawable: T) = drawable.draw(renderer)
}
