package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.util.GameConfiguration

trait PlayerKiller extends SummoningSickness {
	self: Collidable =>

	protected val maxSicknessDurationMillis =
			(GameConfiguration.getProperty("summoning_sickness_in_seconds").toFloat * 1000f).toLong

	def onCollision(collider: Collidable, update: Update) {
		if(collider.isInstanceOf[Player] && !hasSummoningSickness) {
			collider.destroy
		}
	}
}
