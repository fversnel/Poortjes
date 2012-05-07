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

		// Determine which player to follow
		val playerToFollow = fetchPlayerToFollow
		// Calculate the direction in which to go
		if (playerToFollow.isDefined) {
			val playerTranslation = playerToFollow.get.asInstanceOf[Transform].translation
			val angleToTarget = atan2(playerTranslation._2 - translation._2, playerTranslation._1 -	translation._1)
			//logger.info("angle to target: " + angleToTarget)
			setToRotation(angleToTarget)
		}
	}

	private def fetchPlayerToFollow: Option[Player] = {
		// Extract players from the battlefield
		val players = EntityManager().gameObjects.filter(_.is[Player]).map(_.asInstanceOf[Player])

		players.reduceOption(determineNearestPlayer(_, _)) 
	}

	private def determineNearestPlayer(player1: Player, player2: Player): Player = {
		def distanceToPlayer(player: Player): Float = {
			val xDistanceToPlayer = player.translation._1 - translation._1
			val yDistanceToPlayer = player.translation._2 - translation._2

			// pythagorean theorem
			// a2 + b2 = c2
			sqrt(pow(xDistanceToPlayer, 2) + pow(yDistanceToPlayer, 2))
		}

		if(distanceToPlayer(player1) < distanceToPlayer(player2)) player1 else player2
	}


	private implicit def floatToDouble(x: Float): Double = x.toDouble
	private implicit def doubleToFloat(x: Double): Float = x.toFloat
}
