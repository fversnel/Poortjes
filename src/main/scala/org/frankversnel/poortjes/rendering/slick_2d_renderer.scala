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

	def drawRectangle(component: Drawable with Color) {
		drawShapeWithColor(new Rectangle(0, 0, component.width, component.height), component)
	}

	def drawCircle(component: Drawable with Color) {
		drawShapeWithColor(new Ellipse(0, 0, component.width / 2, component.height / 2), component)
	}

	def drawShape(resourceId: ResourceId, component: Drawable) {
		val shape = new Rectangle(0, 0, component.width, component.height)
		drawShape(shape, component) { transformedShape =>
			val image = shapeLoader.getResource(resourceId)
			graphics.fill(transformedShape)
			//graphics.texture(transformedShape, image)
		}
	}

	def clearScreen = { /* No need to implement this method */ }

	private def drawShapeWithColor(shape: Shape, component: Drawable with Color) {
		graphics.setColor(new org.newdawn.slick.Color(component.r, component.g, component.b))
		drawShape(shape, component) { transformedShape =>
			graphics.fill(transformedShape)
		}
	}

	private def drawShape(shape: Shape, component: Transform) (drawFunction: Shape => Unit) {
		val transformedShape = shape.transform(Transform.slickMatrix(component))
		drawFunction(transformedShape)
	}

}
