package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

trait Renderer {
	def drawRectangle(color: Color, transform:  Transform)
	def drawCircle(color : Color, transform : Transform)
	def drawShape(shapeId: String, transform: Transform)

	def clearScreen
}
