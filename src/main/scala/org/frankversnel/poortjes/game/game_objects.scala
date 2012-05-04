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
    protected val shape = resourceLoader.addResource("ship-green.svg")

	override val dimension = (9, 13)

	def onCollision(collider: GameObject) {
		if(collider.is[Candy]) {
			collider.destroy
		}
	}
}
class Shepherd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller {
	protected val shape = resourceLoader.addResource("shepherd.svg")

	override val dimension = (5, 5)
}
class Candy(protected val resourceLoader: ResourceLoader) extends DrawableShape with Collidable {
	protected val shape = resourceLoader.addResource("candy.svg")

	override val dimension = (3, 3)

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
	val dimension = (10, 70)

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
	val dimension = (10, 10)

	protected val shape = resourceLoader.addResource("gate-end.svg")
}
class GateConnector(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) {
	val dimension = (2, 50)

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

class Explosion(private val translation: Tuple2[Float, Float]) extends Drawable with Color with Collidable 
		with TimeBasedLife with Logging {
	val color = Color.red
	val dimension = (100, 100)

	protected val maxTimeAliveMillis = 500
	private val targetRadius = 100f

	translate(translation._1,translation._2)

	override def process(deltaTime: DeltaTime) {
		super.process(deltaTime)

		val actualRadius = targetRadius * timeToLive
		logger.info("radius " + actualRadius)
		//setToScale(actualRadius, actualRadius)
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
