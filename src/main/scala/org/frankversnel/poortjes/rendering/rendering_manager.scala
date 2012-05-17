package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.util.GameConfiguration

class RenderingManager(private val renderer: Renderer,
		private val worldMatrix: Transform) extends ComponentManager with Logging {
	type T = Drawable

	def processComponents(update: Update) {
		renderer.clearScreen

		renderer.pushTransform(worldMatrix)

		allComponents.foreach(_.draw(renderer))
		if(GameConfiguration.isDebugEnabled) {
			drawFramerate(update.framerate)
		}

		renderer.popTransform
	}

	private def drawFramerate(framerate: Long) {
		renderer.drawText(new Transform {
				var dimension = DimensionValue()
				translate(200, 10)},
				framerate + "fps", Color.white)
	}

	protected def isCorrectType(component: Component) = component.isInstanceOf[Drawable]
}
