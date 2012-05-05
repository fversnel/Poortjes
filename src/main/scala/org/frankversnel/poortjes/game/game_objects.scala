package org.frankversnel.poortjes.game

import scala.math._
import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._
import org.frankversnel.poortjes.util.DeltaTime

import org.frankversnel.poortjes._

abstract class Player(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with Collidable with Moveable
		with Keyboard with Speed {
	//speed
	val distanceInMs = 3f
	val rotationInMs = 0.10f

    protected val shape = resourceLoader.addResource("ship-green.svg")

	var dimension = (9, 13)

	def onCollision(collider: GameObject) {
		if(collider.is[Candy]) {
			collider.destroy
		}
	}
}
class Shepherd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller with Moveable with Speed with Logging {
	//speed
	val distanceInMs = 1f
	val rotationInMs = 0.10f

	protected val shape = resourceLoader.addResource("shepherd.svg")

	var dimension = (10, 10)

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
			logger.info("angle to target " + angleToTarget)
			rotationSpeed = angleToTarget.toFloat
		}
	}

	private implicit def floatToDouble(x: Float): Double = x.toDouble
}
class Candy(protected val resourceLoader: ResourceLoader) extends DrawableShape with Collidable {
	protected val shape = resourceLoader.addResource("candy.svg")

	var dimension = (5, 5)

	def onCollision(collider: GameObject) {
		// Do nothing
	}
}

trait PlayerKiller extends Collidable {
	def onCollision(collider: GameObject) {
		if(collider.is[Player]) {
			collider.destroy
		}
	}
}


class Gate extends Transform with Speed with Moveable {
	var dimension = (10, 70)

	val distanceInMs = 0.3f
	val rotationInMs = 0.01f

	moveSpeed = (math.random.toFloat, math.random.toFloat)
	rotationSpeed = 1f
}
abstract class GateComponent(private val _parent: Gate)
		extends DrawableShape with Collidable {

	parent = _parent
}
class GateEnd(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) with PlayerKiller {
	var dimension = (10, 10)

	protected val shape = resourceLoader.addResource("gate-end.svg")
}
class GateConnector(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) {
	var dimension = (2, 50)

	protected val shape = resourceLoader.addResource("gate-connector.svg")

	def onCollision(collider: GameObject) {
		if(collider.is[Player]) {
			val explosionX = matrixStack.translation._1
			val explosionY = matrixStack.translation._2
			EntityManager().spawn(new Explosion(explosionX, explosionY))
			parent.get.destroy
		}
	}
}
object Gate {
	def build(resourceLoader: ResourceLoader): Gate = {
		val gate = new Gate

		val gateEndTop = new GateEnd(resourceLoader, gate)
		gateEndTop.rotate((Pi / 2).toFloat)
		val gateConnector = new GateConnector(resourceLoader, gate)
		gateConnector.translate(4, 10)
		val gateEndBottom = new GateEnd(resourceLoader, gate)
		gateEndBottom.translate(0, 60)
		gateEndBottom.rotate((Pi + (Pi / 2)).toFloat)

		gate
	}
}

class Explosion(private val x: Float, private val y: Float) extends Drawable with Color with Collidable
		with TimeBasedLife {
	val color = Color.red
	var dimension = (1, 1)

	protected val maxTimeAliveMillis = 500
	private val targetRadius = 130f

	translate(x, y)

	override def process(deltaTime: DeltaTime) {
		super.process(deltaTime)

		val actualRadius = targetRadius * timeToLive
		dimension = (actualRadius.toInt, actualRadius.toInt)
	}

	def onCollision(collider: GameObject) {
		if(collider.is[Shepherd]) {
			collider.destroy
		}
	}

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this)
	}
}
