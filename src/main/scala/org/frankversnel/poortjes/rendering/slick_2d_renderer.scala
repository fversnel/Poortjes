package org.frankversnel.poortjes.rendering

import org.newdawn.slick.Graphics

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading._

class Slick2DRenderer(
		private val graphics: Graphics,
		private val shapeLoader: SlickImageLoader) extends Renderer {

	def drawRectangle(component: Transform with Color) {
		draw(component) {
			graphics.fillRect(0, 0, component.width, component.height)
		}
	}

	def drawCircle(component: Transform with Color) {
		draw(component) {
			graphics.fillOval(0, 0, component.width, component.height)
		}
	}

	def drawShape(resourceId: ResourceId, transform: Transform) {
		drawTransform(transform) {
			// load image
			graphics.drawImage(shapeLoader.getResource(resourceId), 0, 0)
			// load svg
			//SimpleDiagramRenderer.render(graphics, shapeLoader.getResource(resourceId))
		}
	}

	def clearScreen = { /* No need to implement this method */ }

	private def draw(component: Transform with Color) (drawFunction: => Unit) {
		graphics.setColor(new org.newdawn.slick.Color(component.r, component.g, component.b))
		drawTransform(component)(drawFunction)
	}

	private def drawTransform(transform: Transform) (drawFunction: => Unit) {
		graphics.pushTransform

		graphics.translate(transform.positionX, transform.positionY)
		graphics.scale(transform.scaleX, transform.scaleY)
		graphics.rotate(transform.width / 2, transform.height / 2, transform.rotationAngleInDegrees)

		drawFunction

		graphics.popTransform
	}
}
