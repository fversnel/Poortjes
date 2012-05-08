package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.util.DeltaTime

class Explosion(private val resourceLoader: ResourceLoader) extends Drawable with Color with Collidable
		with TimeBasedLife {
	val color = Color.red
	var dimension = (1, 1)

	protected val maxTimeAliveMillis = 2000
	private val targetRadius = 200f

	override def process(update: Update) {
		super.process(update)

		val actualRadius = targetRadius * timeToLive
		dimension = (actualRadius.toInt, actualRadius.toInt)
	}

	def onCollision(collider: GameObject) {
		if(collider.is[Shepherd]) {
			collider.destroy

			val shepherdTransform = collider.asInstanceOf[Transform]
			for(i <- 0 until 2) {
				new Spawner(15).spawn(shepherdTransform.translation._1, shepherdTransform.translation._2, new Candy(resourceLoader))
			}
		}
	}

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this)
	}
}
