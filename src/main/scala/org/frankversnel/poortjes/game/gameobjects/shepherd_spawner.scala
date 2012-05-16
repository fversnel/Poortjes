package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.game.SpawnArea
import org.frankversnel.poortjes.resource_loading.ResourceLoader

class ShepherSpawner(private val spawnArea: SpawnArea,
		private val resourceLoader: ResourceLoader) extends Component {
	private val MaxNumberOfShepherds = 100
	private val MaxNumberOfShepherdsAtOnce = 10

	private var spawnIntervalInSeconds = 5f
	private var spawnTime = spawnIntervalInSeconds

	override def process(update: Update) {
		spawnTime -= update.deltaTime.seconds

		lazy val isSpaceLeft = update.gameObjects.filter(_.isInstanceOf[Shepherd]).size < MaxNumberOfShepherds
		val isTimeToSpawn = spawnTime <= 0f
		if(isTimeToSpawn && isSpaceLeft) {
			for(i <- 0 until MaxNumberOfShepherdsAtOnce) {
				spawnArea.spawn(new Shepherd(resourceLoader))
				spawnTime = spawnIntervalInSeconds
			}
		}
	}
}
