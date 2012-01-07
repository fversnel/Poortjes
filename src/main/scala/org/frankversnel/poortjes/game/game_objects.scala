package org.frankversnel.poortjes.game

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
}
abstract class StillObject extends Drawable with Collidable with Color {

	override def draw(renderer: Renderer) {
		renderer.drawCircle(this)
	}
}


class Gate extends Transform {
    val dimension = Dimension(0, 0)
}
abstract class GateComponent(private val _parent: Gate)
        extends DrawableShape with Collidable {

    override val parent = Some(_parent)
}
class GateEnd(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) {
    val dimension = Dimension(20, 20)

    protected val shape = resourceLoader.addResource("gate-end.svg")
}
class GateConnector(protected val resourceLoader: ResourceLoader, private val _parent: Gate)
        extends GateComponent(_parent) {
    val dimension = Dimension(4, 30)

    protected val shape = resourceLoader.addResource("gate-connector.svg")
}
object Gate {
    def build(resourceLoader: ResourceLoader): List[GameObject] = {
        val gate = new Gate
        gate.translate(300, 200)

        val gateEndTop = new GateEnd(resourceLoader, gate)
        gateEndTop.translate(0, 0)
        val gateConnector = new GateConnector(resourceLoader, gate)
        gateConnector.translate(8, 20)
        val gateEndBottom = new GateEnd(resourceLoader, gate)
        gateEndBottom.translate(0, 50)

        List(gateEndBottom, gateEndTop, gateConnector)
    }
}
