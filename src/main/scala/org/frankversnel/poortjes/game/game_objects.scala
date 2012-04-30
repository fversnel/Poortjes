package org.frankversnel.poortjes.game

import scala.math._

import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._

import org.frankversnel.poortjes._

abstract class Player(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with Collidable with Moveable
		with Keyboard with Speed {
    protected val shape = resourceLoader.addResource("ship-red.svg")

	override val dimension = (9, 13)

	override def collidesWith(otherCollidable: Collidable) = {
		val collision = super.collidesWith(otherCollidable)
		if(collision && otherCollidable.is[Candy]) {
			otherCollidable.destroy
		}
		collision
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
}

trait PlayerKiller extends Collidable {
	override def collidesWith(otherCollidable: Collidable): Boolean = {
		val collision = super.collidesWith(otherCollidable)
		if(collision && otherCollidable.is[Player]) {
			otherCollidable.destroy
		}
		collision
	}
}


class Gate extends Transform with Speed with Moveable {
	val dimension = (10, 70)

	val distanceInMs = 0.3f
	val rotationInMs = 0.01f

	override def move {
		moveSpeed = (math.random.toFloat, math.random.toFloat)
		rotationSpeed = 1f

		super.move
	}
}
abstract class GateComponent(private val _parent: Gate)
		extends DrawableShape with Collidable {

	setParent(_parent)

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

	override def collidesWith(otherCollidable: Collidable): Boolean = {
		val collision = super.collidesWith(otherCollidable)
		if(collision && otherCollidable.is[Player]) {
			// TODO Spawn explosion
			parent.get.destroy
		}
		collision
	}
}
object Gate {
	def build(resourceLoader: ResourceLoader): Gate = {
		val gate = new Gate
		gate.translate(300, 200)

		val gateEndTop = new GateEnd(resourceLoader, gate)
		gateEndTop.rotate((Pi / 2).toFloat)
		val gateConnector = new GateConnector(resourceLoader, gate)
		gateConnector.translate(4, 10)
		val gateEndBottom = new GateEnd(resourceLoader, gate)
		gateEndBottom.translate(0, 60)
		gateEndBottom.rotate((Pi + (Pi / 2)).toFloat)

		gate.rotate(0.5f)

		gate
	}
}
