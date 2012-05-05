package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Color
import org.frankversnel.poortjes.rendering.Drawable
import org.frankversnel.poortjes.rendering.Renderer
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.util.DeltaTime

class Explosion(private val x: Float, private val y: Float) extends Drawable with Color with Collidable
		with TimeBasedLife {
	val color = Color.red
	var dimension = (1, 1)

	protected val maxTimeAliveMillis = 500
	private val targetRadius = 130f

	translate(x, y)

	override def process(deltaTime: DeltaTime) {
		super.process(deltaTime)

		val actualRadius = targetRadius * timeToLive
		dimension = (actualRadius.toInt, actualRadius.toInt)
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
