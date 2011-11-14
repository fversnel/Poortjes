package org.frankversnel.poortjes.rendering

import org.slf4j.scala.Logging
import processing.core.PGraphics

import org.frankversnel.poortjes._

import org.frankversnel.poortjes.resource_loading.ProcessingShapeLoader

class Processing2DRenderer(
	private val graphics: PGraphics,
	private val shapeLoader: ProcessingShapeLoader,
	private val backgroundColor: Int) extends Renderer {

	def drawRectangle(color: Color, transform:  Transform) {
		draw(transform, color) {
			graphics.rect(0, 0, transform.width, transform.height)
		}
	}

	def drawCircle(color: Color, transform:  Transform) {
		draw(transform, color) {
			graphics.ellipse(0, 0, transform.width, transform.height)
		}
	}

	def drawShape(shapeId: String, transform: Transform) {
		draw(transform) {
			graphics.shape(shapeLoader.getResource(shapeId),
					0, 0, transform.width, transform.height)
		}
	}

	def clearScreen = graphics.background(backgroundColor)

	private def draw(transform: Transform, color: Color) (drawFunction: => Unit) {
		fill(color)
		draw(transform)(drawFunction)
	}

	private def draw(transform: Transform) (drawFunction: => Unit) {
		graphics.setMatrix(transform.processingMatrix)
		graphics.pushMatrix

		drawFunction

		graphics.popMatrix
	}

	private def fill(c: Color) = graphics.fill(c.r, c.g, c.b)

}
