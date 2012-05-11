package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.game.SpawnArea
import org.frankversnel.poortjes.resource_loading.ResourceLoader

class GateSpawner(private val spawnArea: SpawnArea,
		private val resourceLoader: ResourceLoader) extends Component {
	private val SpawnIntervalInSeconds = 3f
	private val MaxNumberOfGates = 10

	private var spawnTime = SpawnIntervalInSeconds

	override def process(update: Update) {
		spawnTime -= update.deltaTime.seconds

		val isSpaceLeft = update.gameObjects.filter(_.is[Gate]).size < MaxNumberOfGates
		val isTimeToSpawn = spawnTime <= 0f
		if(isTimeToSpawn && isSpaceLeft) {
			spawnArea.spawn(Gate.build(resourceLoader))
			spawnTime = SpawnIntervalInSeconds
		}
	}
}
