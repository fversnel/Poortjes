package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.resource_loading.ResourceId

trait Renderer {
	def drawRectangle(component: Drawable with Color)
	def drawCircle(component: Drawable with Color)
	def drawShape(resourceId: ResourceId, component: Drawable)

	def clearScreen
}
