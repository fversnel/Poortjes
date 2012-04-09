package org.frankversnel.poortjes.game

import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.moving._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.resource_loading._

import org.frankversnel.poortjes._

class PlayerParent extends GameObject with Transform {
	val dimension = (50, 50)
	translate(200, 200)
	rotate(1)
}

abstract class Player(val ship: ResourceId)
		extends GameObject with Drawable with Collidable with Moveable
		with Keyboard with Speed {
	override val parent = Some(new PlayerParent)

	override def draw(renderer: Renderer) {
		renderer.drawShape(ship, this)
	}
}
abstract class StillObject extends Drawable with Collidable with Color {

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this)
	}
}


class Gate extends Transform {
    val dimension = (0, 0)
}
class GateEnd(private val resourceLoader: ProcessingShapeLoader, private val _parent: GameObject)
        extends Drawable with Collidable {
    val dimension = (20, 20)

    override val parent = Some(_parent)
    private val end = resourceLoader.addResource("gate-end.svg")

    override def draw(renderer: Renderer) {
        renderer.drawShape(end, this)
    }
}
class GateConnector(private val resourceLoader: ProcessingShapeLoader, private val _parent: GameObject)
        extends Drawable with Collidable {
    val dimension = (10, 50)

    override val parent = Some(_parent)
    private val connector = resourceLoader.addResource("gate-connector.svg")

    override def draw(renderer: Renderer) {
        renderer.drawShape(connector, this)
    }
}
object Gate {
    def build(resourceLoader: ProcessingShapeLoader): List[GameObject] = {
        val gate = new Gate
        gate.translate(300, 200)

        val gateEndBottom = new GateEnd(resourceLoader, gate)
        gateEndBottom.translate(0, 50)
        val gateEndTop = new GateEnd(resourceLoader, gate)
        gateEndTop.translate(0, -50)
        val gateConnector = new GateConnector(resourceLoader, gate)

        List(gateEndBottom, gateEndTop, gateConnector)
    }
}
