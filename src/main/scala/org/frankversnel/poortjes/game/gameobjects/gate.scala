package org.frankversnel.poortjes.game.gameobjects

import scala.math._

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.util.GameConfiguration

class Gate extends Transform with Speed with Moveable {
	var dimension = DimensionValue().width(10).height(70)

	val distanceInMs = GameConfiguration.getProperty("gate_move_speed").toFloat
	val rotationInMs = GameConfiguration.getProperty("gate_rotation_speed").toFloat

	moveSpeed = (1f, 1f)
	rotationSpeed = 1f
}
class GateEnd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller with CollidableCircle {
	var dimension = DimensionValue().width(10).height(10)

	protected val shape = resourceLoader.addResource("gate-end.svg")
}
class GateConnector(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with CollidableRectangle {
	var dimension = DimensionValue().width(2).height(50)

	protected val shape = resourceLoader.addResource("gate-connector.svg")

	def onCollision(collider: Collidable, update: Update) {
		if(collider.isInstanceOf[Player]) {
			val explosion = new Explosion
			explosion.translate(matrixStack.translation._1, matrixStack.translation._2)
			EntityManager().spawn(explosion)

			parent.get.destroy
		}
	}
}
object Gate {
	def build(resourceLoader: ResourceLoader): Gate = {
		val gate = new Gate

		val gateEndTop = new GateEnd(resourceLoader)
		gateEndTop.parent = gate
		gateEndTop.rotate((Pi / 2).toFloat)
		val gateConnector = new GateConnector(resourceLoader)
		gateConnector.parent = gate
		gateConnector.translate(4, 10)
		val gateEndBottom = new GateEnd(resourceLoader)
		gateEndBottom.parent = gate
		gateEndBottom.translate(0, 60)
		gateEndBottom.rotate((Pi + (Pi / 2)).toFloat)

		gate
	}
}
