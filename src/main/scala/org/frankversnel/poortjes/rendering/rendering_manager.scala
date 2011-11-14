package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

class RenderingManager(private val renderer: Renderer) extends ComponentManager[Drawable] {

	protected override def processComponents = {
		renderer.clearScreen
		allComponents.foreach(processComponent _)
	}

	protected def processComponent(drawable: Drawable) = drawable.draw(renderer)
}
