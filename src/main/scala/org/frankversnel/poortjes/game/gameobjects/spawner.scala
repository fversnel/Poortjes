package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.EntityManager
import org.frankversnel.poortjes.Transform

class Spawner(val areaSize: Int) {

	def spawn(x: Float, y: Float, spawnableObject: GameObject with Transform) {
		spawnableObject.translate(randomSpawnCoord(x), randomSpawnCoord(y))
		EntityManager().spawn(spawnableObject)
	}

	private def randomSpawnCoord(coord: Float): Float = {
		coord + (Spawner.random.nextInt(areaSize * 2) - areaSize).toFloat
	}
}
import scala.util.Random
object Spawner {
	private val random = new Random(System.currentTimeMillis)
}
