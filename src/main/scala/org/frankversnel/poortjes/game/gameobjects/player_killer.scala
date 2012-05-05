package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.collision.Collidable

trait PlayerKiller extends Collidable {
	def onCollision(collider: GameObject) {
		if(collider.is[Player]) {
			collider.destroy
		}
	}
}
