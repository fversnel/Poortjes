package org.frankversnel.poortjes.game.gameobjects

import scala.math._
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.EntityManager
import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.moving._

class Shepherd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller with Moveable with Speed with Logging {
	//speed
	val distanceInMs = 1f
	val rotationInMs = 0.10f
	moveSpeed = (1, 0)

	protected val shape = resourceLoader.addResource("shepherd.svg")

	var dimension = (8, 8)

	override def process {
		super.process

		// Extract players from the battlefield
		val players = EntityManager().gameObjects.filter(_.is[Player])
		// Determine which player to follow
		val playerToFollow = players.headOption
		// Calculate the direction in which to go
		if (playerToFollow.isDefined) {
			val playerTranslation = playerToFollow.get.as[Transform].get.translation
			val angleToTarget = atan2(playerTranslation._2 - translation._2, playerTranslation._1 -	translation._1)
			logger.info("angle to target: " + angleToTarget)
			val rotationAngle = angleToTarget - angle
			logger.info("rotation angle: " + angleToTarget)
			rotationSpeed = rotationAngle
		}
	}

	private implicit def floatToDouble(x: Float): Double = x.toDouble
	private implicit def doubleToFloat(x: Double): Float = x.toFloat
}
