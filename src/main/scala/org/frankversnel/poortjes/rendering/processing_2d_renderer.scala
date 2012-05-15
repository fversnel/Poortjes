package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging
import processing.core.PGraphics

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading._

class Processing2DRenderer(
	private val graphics: PGraphics,
	private val shapeLoader: ProcessingShapeLoader,
	private val backgroundColor: Int) extends Renderer {

	def drawText(transform: Transform, text: String, color: Color) {
		draw(transform) {
			fill(color)
			graphics.text(text, 0, 0)
		}
	}

	def drawRectangle(transform: Transform, color: Color) {
		draw(transform) {
			fill(color)
			graphics.rect(0, 0, transform.dimension.width, transform.dimension.height)
		}
	}

	def drawCircle(transform: Transform, color: Color) {
		draw(transform) {
			fill(color)
			graphics.ellipse(0, 0, transform.dimension.width, transform.dimension.height)
		}
	}

	def drawShape(transform: Transform, resourceId: ResourceId) {
		draw(transform) {
			graphics.shape(shapeLoader.getResource(resourceId),
					0, 0, transform.dimension.width, transform.dimension.height)
		}
	}

	def clearScreen = graphics.background(backgroundColor)

	private def draw(transform: Transform) (drawFunction: => Unit) {
		graphics.pushMatrix
		graphics.setMatrix(Transform.processingMatrix(transform))

		drawFunction

		graphics.popMatrix
	}

	private def fill(color: Color) {
		graphics.fill(color.r, color.g, color.b, color.a)
	}
}
