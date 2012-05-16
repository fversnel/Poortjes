package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.game.SpawnArea
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.util.GameConfiguration

class GateSpawner(private val spawnArea: SpawnArea,
		private val resourceLoader: ResourceLoader) extends Component {
	private val SpawnIntervalInSeconds =
			GameConfiguration.getProperty("gate_spawn_interval_in_seconds").toFloat
	private val MaxNumberOfGates = 
			GameConfiguration.getProperty("max_number_of_gates").toInt

	private var spawnTime = SpawnIntervalInSeconds

	override def process(update: Update) {
		spawnTime -= update.deltaTime.seconds

		lazy val isSpaceLeft = update.gameObjects.filter(_.isInstanceOf[Gate]).size < MaxNumberOfGates
		val isTimeToSpawn = spawnTime <= 0f
		if(isTimeToSpawn && isSpaceLeft) {
			spawnArea.spawn(Gate.build(resourceLoader))
			spawnTime = SpawnIntervalInSeconds
		}
	}
}
