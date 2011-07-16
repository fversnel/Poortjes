package org.frankversnel.nl.poortjes.game;

import org.slf4j.scala.Logging;

abstract class GameObject

trait Component extends GameObject

class Color(val r: Int, val g: Int, val b: Int)

class Position(val x: Float, val y: Float)

trait Drawable extends Component {
	val color: Color
	val position: Position

	def draw(renderer: Renderer) {
		renderer.drawCircle(color, position)
	}
}

trait Collision extends Component {
	val position: Position
}

abstract class Renderer {
	def drawCircle(color : Color, position : Position)
}

object RendererImpl extends Renderer with Logging {
	def drawCircle(color : Color, position : Position) {
		logger.info("rendering color r: %d, g: %d, b: %d on position x: %f, y: %f"
			.format( color.r, color.g, color.b, position.x, position.y))
	}
}

class Player(val color: Color, val position: Position)
		extends GameObject with Drawable with Collision
