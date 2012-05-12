package org.frankversnel.poortjes.game.gameobjects

import scala.math._

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.moving._

class Gate extends Transform with Speed with Moveable {
	var dimension = DimensionValue().width(10).height(70)

	val distanceInMs = 0.001f
	val rotationInMs = 0.0002f

	moveSpeed = (math.random.toFloat, math.random.toFloat)
	rotationSpeed = 1f
}
class GateEnd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller {
	var dimension = DimensionValue().width(10).height(10)

	protected val shape = resourceLoader.addResource("gate-end.svg")
}
class GateConnector(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with Collidable with SummoningSickness {
	protected val maxSicknessDurationMillis = 1000L

	var dimension = DimensionValue().width(2).height(50)

	protected val shape = resourceLoader.addResource("gate-connector.svg")

	def onCollision(collider: GameObject) {
		if(collider.is[Player] && !hasSummoningSickness) {
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
