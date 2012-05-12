package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging
import processing.core.PGraphics

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading._

class Processing2DRenderer(
	private val graphics: PGraphics,
	private val shapeLoader: ProcessingShapeLoader,
	private val backgroundColor: Int) extends Renderer {

	def drawText(text: String, color: Color, x: Int, y: Int) {
		fill(color)
		graphics.text(text, x, y)
	}

	def drawRectangle(transform: Transform, color: Color) {
		draw(transform, color) {
			graphics.rect(0, 0, transform.dimension.width, transform.dimension.height)
		}
	}

	def drawCircle(transform: Transform, color: Color) {
		draw(transform, color) {
			graphics.ellipse(0, 0, transform.dimension.width, transform.dimension.height)
		}
	}

	def drawShape(transform: Transform, resourceId: ResourceId) {
		drawTransform(transform) {
			graphics.shape(shapeLoader.getResource(resourceId),
					0, 0, transform.dimension.width, transform.dimension.height)
		}
	}

	def clearScreen = graphics.background(backgroundColor)

	private def draw(transform: Transform, color: Color) (drawFunction: => Unit) {
		fill(color)
		drawTransform(transform)(drawFunction)
	}

	private def drawTransform(transform: Transform) (drawFunction: => Unit) {
		graphics.setMatrix(Transform.processingMatrix(transform))
		graphics.pushMatrix

		drawFunction

		graphics.popMatrix
		graphics.resetMatrix
	}

	private def fill(color: Color) {
		graphics.fill(color.r, color.g, color.b, color.a)
	}
}
