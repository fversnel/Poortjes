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

	def pushTransform(transform: Transform) {
		graphics.pushMatrix
		graphics.translate(transform.translation._1, transform.translation._2)
		graphics.rotate(transform.rotationAngle)
		graphics.scale(transform.scale._1, transform.scale._2)
	}
	def popTransform {
		graphics.popMatrix
	}

	private def draw(transform: Transform) (drawFunction: => Unit) {
		pushTransform(transform)
		drawFunction
		popTransform
	}

	private def fill(color: Color) {
		graphics.fill(color.r, color.g, color.b, color.a)
	}
}
