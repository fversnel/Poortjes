package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading.ResourceId

trait Renderer {
	def drawText(transform: Transform, text: String, color: Color)
	def drawRectangle(transform: Transform, color: Color)
	def drawCircle(transform: Transform, color: Color)
	def drawShape(transform: Transform, resourceId: ResourceId)

	def pushTransform(transform: Transform)
	def popTransform

	def clearScreen
}
