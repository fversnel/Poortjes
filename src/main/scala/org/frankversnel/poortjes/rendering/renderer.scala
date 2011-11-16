package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

trait Renderer {
	def drawRectangle(component: Transform with Color)
	def drawCircle(component: Transform with Color)
	def drawShape(shapeId: String, transform: Transform)

	def clearScreen
}
