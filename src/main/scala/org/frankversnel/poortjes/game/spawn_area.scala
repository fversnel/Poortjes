package org.frankversnel.poortjes.game

import org.frankversnel.poortjes._

class SpawnArea(private val entityManager: EntityManager,
        private val area: Area) {

    def spawn(gameObject: GameObject with Transform) {
        val spawnLocation = fetchRandomSpawnLocation
        gameObject.translate(spawnLocation.x, spawnLocation.y)
        entityManager.spawn(gameObject)
    }

    private case class SpawnLocation(x: Int, y: Int)
    private def fetchRandomSpawnLocation = {
        def fetchRandomSpawnPoint(min: Int, max: Int) = {
            SpawnArea.random.nextInt(max - min) + min
        }
        SpawnLocation(fetchRandomSpawnPoint(area.minX, area.maxX),
                fetchRandomSpawnPoint(area.minY, area.maxY))
    }
}
object SpawnArea {
    import scala.util.Random
	private val random = new Random(System.currentTimeMillis)
}

case class Area(minX: Int, maxX: Int, minY: Int, maxY: Int)
