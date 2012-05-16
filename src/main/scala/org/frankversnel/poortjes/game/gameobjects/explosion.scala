package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.collision.CollidableCircle
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.util.DeltaTime
import org.frankversnel.poortjes.util.GameConfiguration

class Explosion extends Drawable with CollidableCircle with TimeBasedLife {
	val color = Color.red.a(125)
	var dimension = DimensionValue().width(0).height(0)

	protected val maxTimeAliveMillis = 
			(GameConfiguration.getProperty("explosion_duration_in_seconds").toFloat * 1000f).toLong
	private val targetRadius =
			GameConfiguration.getProperty("explosion_impact_radius").toFloat

	override def process(update: Update) {
		super.process(update)

		val actualRadius = targetRadius * timeToLive
		dimension = dimension.width(actualRadius.toInt).height(actualRadius.toInt)
	}

	def onCollision(collider: Collidable, update: Update) {
		if(collider.isInstanceOf[Shepherd]) {
			val score = update.gameObjects.filter(_.isInstanceOf[Score]).headOption
			if(score.isDefined) {
				score.get.asInstanceOf[Score].incrementScore
			}

			collider.destroy
		}
	}

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this, color)
	}
}
