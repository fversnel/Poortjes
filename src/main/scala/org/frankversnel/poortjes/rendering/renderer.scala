package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading.ResourceId

trait Renderer {
	def drawText(text: String, color: Color, x: Int, y: Int)
	def drawRectangle(component: Transform, color: Color)
	def drawCircle(component: Transform, color: Color)
	def drawShape(transform: Transform, resourceId: ResourceId)

	def clearScreen
}
