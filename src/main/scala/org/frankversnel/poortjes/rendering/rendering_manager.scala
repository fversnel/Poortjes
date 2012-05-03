package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.util.DeltaTime

class RenderingManager(private val renderer: Renderer) extends ComponentManager {
	type T = Drawable

	override def processComponents(deltaTime: DeltaTime) = {
		renderer.clearScreen
		super.processComponents(deltaTime)
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]

	protected def processComponent(drawable: T, deltaTime: DeltaTime) = drawable.draw(renderer)
}
