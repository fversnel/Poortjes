package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging
import processing.core.PGraphics

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading._

class Processing2DRenderer(
	private val graphics: PGraphics,
	private val shapeLoader: ProcessingShapeLoader,
	private val backgroundColor: Int) extends Renderer {

	def drawRectangle(component: Drawable with Color) {
		draw(component) {
			graphics.rect(0, 0, component.dimension.width, component.dimension.height)
		}
	}

	def drawCircle(component: Drawable with Color) {
		draw(component) {
			graphics.ellipse(0, 0, component.dimension.width, component.dimension.height)
		}
	}

	def drawShape(resourceId: ResourceId, component: Drawable) {
		drawTransform(component) {
			graphics.shape(shapeLoader.getResource(resourceId),
					0, 0, component.dimension.width, component.dimension.height)
		}
	}

	def clearScreen = graphics.background(backgroundColor)

	private def draw(component: Drawable with Color) (drawFunction: => Unit) {
		graphics.fill(component.color.r, component.color.g, component.color.b, component.color.a)
		drawTransform(component)(drawFunction)
	}

	private def drawTransform(transform: Transform) (drawFunction: => Unit) {
		graphics.setMatrix(Transform.processingMatrix(transform))
		graphics.pushMatrix

		drawFunction

		graphics.popMatrix
	}

}
