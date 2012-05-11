package org.frankversnel.poortjes.game.gameobjects

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.game.SpawnArea
import org.frankversnel.poortjes.game.Area

class Shepherd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller with Moveable with Speed with Logging {
	private val NumberOfCandiesToSpawn = 2

	//speed
	val distanceInMs = 0.05f
	val rotationInMs = 0f
	moveSpeed = (1, 0)

	var dimension = DimensionValue().width(8).height(8)

	protected val shape = resourceLoader.addResource("shepherd.svg")

	override def destroy {
		for(i <- 0 until NumberOfCandiesToSpawn) {
			val spawnArea = new SpawnArea(EntityManager(), 
				Area(translation._1.toInt - 10, translation._1.toInt + 10, 
					translation._2.toInt - 10, translation._2.toInt + 10))
			spawnArea.spawn(new Candy(resourceLoader))
		}

		super.destroy
	}

	override def process(update: Update) {
		super.process(update)

		val players = update.gameObjects.filter(_.is[Player]).map(_.asInstanceOf[Player])
		val playerToFollow = fetchNearestOf(players)
		if (playerToFollow.isDefined) {
			setToRotation(angleTo(playerToFollow.get))
		}
	}
}
