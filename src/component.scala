package org.frankversnel.nl.poortjes.game;

import org.slf4j.scala.Logging;

abstract class GameObject

trait Color extends GameObject {
    val r: Int
    val g: Int
    val b: Int
}

trait Position extends GameObject {
    val x: Float
    val y: Float
}

trait Drawable extends GameObject with Color with Position {
    def draw(renderer: Renderer) {
        renderer.drawCircle(this, this)
    }
}

trait Collision extends GameObject with Position

class Renderer extends Logging {
    def drawCircle(color : Color, position : Position) {
		logger.info("rendering color r: %d, g: %d, b: %d on position x: %f, y: %f"
				.format( color.r, color.g, color.b, position.x, position.y))
    }
}

class Player(val x: Float, val y: Float, val r: Int, val g: Int, val b: Int)
        extends GameObject with Drawable with Collision
