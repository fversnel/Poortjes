package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes.resource_loading._

import org.frankversnel.poortjes._

trait DrawableShape extends Drawable {
	protected val resourceLoader: ResourceLoader
	protected val shape: ResourceId

	override def draw(renderer: Renderer) {
		renderer.drawShape(this, shape)
	}
}
