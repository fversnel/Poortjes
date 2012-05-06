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
import org.frankversnel.poortjes.util.DeltaTime

class Shepherd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller with Moveable with Speed with Logging {
	//speed
	val distanceInMs = 0.05f
	val rotationInMs = 0f
	moveSpeed = (1, 0)

	protected val shape = resourceLoader.addResource("shepherd.svg")

	var dimension = (8, 8)

	override def process(deltaTime: DeltaTime) {
		super.process(deltaTime)

		// Extract players from the battlefield
		val players = EntityManager().gameObjects.filter(_.is[Player])
		// Determine which player to follow
		val playerToFollow = players.headOption
		// Calculate the direction in which to go
		if (playerToFollow.isDefined) {
			val playerTranslation = playerToFollow.get.as[Transform].get.translation
			val angleToTarget = atan2(playerTranslation._2 - translation._2, playerTranslation._1 -	translation._1)
			//logger.info("angle to target: " + angleToTarget)
			setToRotation(angleToTarget)
		}
	}

	private implicit def floatToDouble(x: Float): Double = x.toDouble
	private implicit def doubleToFloat(x: Double): Float = x.toFloat
}
