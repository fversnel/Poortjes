package org.frankversnel.poortjes.rendering

import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Ellipse
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Shape

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading._

class Slick2DRenderer(
	private val graphics: Graphics,
	private val shapeLoader: SlickImageLoader) extends Renderer {

	def drawText(transform: Transform, text: String, color: Color) {
		throw new RuntimeException("TODO Implement this method")
	}

	def drawRectangle(component: Transform, color: Color) {
		drawShapeWithColor(new Rectangle(0, 0, component.dimension.width,
				component.dimension.height), component, color)
	}

	def drawCircle(component: Transform, color: Color) {
		drawShapeWithColor(new Ellipse(0, 0, component.dimension.width / 2,
				component.dimension.height / 2), component, color)
	}

	def drawShape(component: Transform, resourceId: ResourceId) {
		val shape = new Rectangle(0, 0, component.dimension.width, component.dimension.height)
		drawShape(shape, component) { transformedShape =>
			val image = shapeLoader.getResource(resourceId)
			graphics.fill(transformedShape)
			//graphics.texture(transformedShape, image)
		}
	}

	def clearScreen = { /* No need to implement this method */ }

	private def drawShapeWithColor(shape: Shape, component: Transform, color: Color) {
		fill(color)
		drawShape(shape, component) { transformedShape =>
			graphics.fill(transformedShape)
		}
	}

	private def drawShape(shape: Shape, component: Transform) (drawFunction: Shape => Unit) {
		val transformedShape = shape.transform(Transform.slickMatrix(component))
		drawFunction(transformedShape)
	}

	private def fill(color: Color) {
		graphics.setColor(new org.newdawn.slick.Color(color.r, color.g,	color.b, color.a))
	}

}
