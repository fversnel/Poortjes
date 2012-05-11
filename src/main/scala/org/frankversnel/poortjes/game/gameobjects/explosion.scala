package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.collision.CollidableCircle
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.util.DeltaTime

class Explosion extends Drawable with Color with CollidableCircle
		with TimeBasedLife {
	val color = ColorValue().r(255).a(125)
	var dimension = DimensionValue().width(0).height(0)

	protected val maxTimeAliveMillis = 2000L
	private val targetRadius = 200f

	override def process(update: Update) {
		super.process(update)

		val actualRadius = targetRadius * timeToLive
		dimension = dimension.width(actualRadius.toInt).height(actualRadius.toInt)
	}

	def onCollision(collider: GameObject) {
		if(collider.is[Shepherd]) {
			collider.destroy
		}
	}

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this)
	}
}
