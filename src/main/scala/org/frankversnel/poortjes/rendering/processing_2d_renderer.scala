package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging
import processing.core.PGraphics

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading._

class Processing2DRenderer(
	private val graphics: PGraphics,
	private val shapeLoader: ProcessingShapeLoader,
	private val backgroundColor: Int) extends Renderer {

	def drawRectangle(component: Transform with Color) {
		draw(component) {
			graphics.rect(0, 0, component.width, component.height)
		}
	}

	def drawCircle(component: Transform with Color) {
		draw(component) {
			graphics.ellipse(0, 0, component.width, component.height)
		}
	}

	def drawShape(resourceId: ResourceId, transform: Transform) {
		drawTransform(transform) {
			graphics.shape(shapeLoader.getResource(resourceId),
					0, 0, transform.width, transform.height)
		}
	}

	def clearScreen = graphics.background(backgroundColor)

	private def draw(component: Transform with Color) (drawFunction: => Unit) {
		fill(component)
		drawTransform(component)(drawFunction)
	}

	private def drawTransform(transform: Transform) (drawFunction: => Unit) {
		graphics.ellipse(200,200, transform.width, transform.height)
		graphics.setMatrix(transform.processingMatrix)
		graphics.pushMatrix

		drawFunction

		graphics.popMatrix
	}

	private def fill(c: Color) = graphics.fill(c.r, c.g, c.b)

}
