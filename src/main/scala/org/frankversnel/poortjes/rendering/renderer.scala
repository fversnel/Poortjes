package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading.ResourceId

trait Renderer {
	def drawRectangle(component: Transform with Color)
	def drawCircle(component: Transform with Color)
	def drawShape(resourceId: ResourceId, transform: Transform)

	def clearScreen
}
