package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.collision.Collidable

trait PlayerKiller extends Collidable with SummoningSickness {
	protected val maxSicknessDurationMillis = 1000L

	def onCollision(player: GameObject) {
		if(!hasSummoningSickness) {
			player.destroy
		}
	}
}
