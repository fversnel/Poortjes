package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.collision.Collidable

trait PlayerKiller extends Collidable with SummoningSickness {
	protected val maxSicknessDurationMillis = 1000L

	def onCollision(collider: Collidable, update: Update) {
		if(collider.isInstanceOf[Player] && !hasSummoningSickness) {
			collider.destroy
		}
	}
}
