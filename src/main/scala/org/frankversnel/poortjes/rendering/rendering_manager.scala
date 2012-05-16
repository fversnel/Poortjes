package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.util.GameConfiguration

class RenderingManager(private val renderer: Renderer) extends ComponentManager {
	type T = Drawable

	def processComponents(update: Update) {
		renderer.clearScreen
		allComponents.foreach(_.draw(renderer))
		
		if(GameConfiguration.isDebugEnabled) {
			// Draw framerate
			renderer.drawText(new Transform {
					var dimension = DimensionValue()
					translate(200, 10)},
					update.framerate + "fps", Color.white)
		}
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]
}
