package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.collision.Collidable

trait PlayerKiller extends Collidable with SummoningSickness {
	protected val maxSicknessDurationMillis = 3000L

	def onCollision(collider: GameObject) {
		if(collider.is[Player] && !hasSummoningSickness) {
			collider.destroy
		}
	}
}
