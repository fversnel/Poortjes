package org.frankversnel.poortjes.game.gameobjects

import scala.math._

import org.frankversnel.poortjes.EntityManager
import org.frankversnel.poortjes.GameObject
import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.collision.Collidable
import org.frankversnel.poortjes.rendering.DrawableShape
import org.frankversnel.poortjes.resource_loading.ResourceLoader
import org.frankversnel.poortjes.moving._

class Gate extends Transform with Speed with Moveable {
	var dimension = (10, 70)

	val distanceInMs = 0.1f
	val rotationInMs = 0.005f

	moveSpeed = (math.random.toFloat, math.random.toFloat)
	rotationSpeed = 1f
}
class GateEnd(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with PlayerKiller {
	var dimension = (10, 10)

	protected val shape = resourceLoader.addResource("gate-end.svg")
}
class GateConnector(protected val resourceLoader: ResourceLoader)
		extends DrawableShape with Collidable {
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
