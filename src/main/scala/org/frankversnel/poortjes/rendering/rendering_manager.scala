package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

class RenderingManager(private val renderer: Renderer) extends ComponentManager with Logging {
	type T = Drawable

	def processComponents(update: Update) {
		renderer.clearScreen
		allComponents.foreach(_.draw(renderer))
		
		//logger.info(update.framerate + "fps")

		// Draw framerate
		renderer.drawText(new Transform {
				var dimension = DimensionValue()
				translate(200, 10)},
				update.framerate + "fps", Color.white)
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]
}
