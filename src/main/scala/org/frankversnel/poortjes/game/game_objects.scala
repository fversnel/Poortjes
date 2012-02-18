package org.frankversnel.poortjes.game

import scala.math._

import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._

import org.frankversnel.poortjes._

abstract class Player(protected val resourceLoader: ResourceLoader)
		extends GameObject with DrawableShape with Collidable with Moveable
		with Keyboard with Speed {
    protected val shape = resourceLoader.addResource("ship-red.svg")

	override val dimension = Dimension(9, 13)
}
class Shepherd(protected val resourceLoader: ResourceLoader)
        extends DrawableShape with Collidable {
    protected val shape = resourceLoader.addResource("shepherd.svg")

	override val dimension = Dimension(10, 10)
}


class Gate extends Transform with Speed with Moveable {
	val dimension = Dimension(10, 70)

	val distanceInMs = 0.3f
	val rotationInMs = 0.01f

	override def move {
		moveSpeed(math.random.toFloat)
		rotationSpeed(1f)

		super.move
	}
}
abstract class GateComponent(private val _parent: Gate)
		extends DrawableShape with Collidable {

	override val parent = Some(_parent)
}
class GateEnd(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) {
	val dimension = Dimension(10, 10)

	protected val shape = resourceLoader.addResource("gate-end.svg")

	override def collidesWith(otherCollidable: Collidable): Boolean = {
		val collision = super.collidesWith(otherCollidable)
			if(collision && otherCollidable.is[Player]) {
			EntityManager().destroy(otherCollidable)
		}
		collision
	}
}
class GateConnector(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) {
	val dimension = Dimension(2, 50)

	protected val shape = resourceLoader.addResource("gate-connector.svg")
}
object Gate {
	def build(resourceLoader: ResourceLoader): List[GameObject] = {
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

		List(gate, gateEndBottom, gateEndTop, gateConnector)
	}
}
