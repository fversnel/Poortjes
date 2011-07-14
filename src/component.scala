package org.frankversnel.nl.poortjes.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

class Renderer {
	private val logger = LoggerFactory.getLogger(getClass);

    def drawCircle(color : Color, position : Position) {
		logger.info("rendering color r: " + color.r + ", g: " + color.g + ", b: " + color.b + 
			" on position x: " + position.x + ", y: " + position.y)
    }
}

class Player(val x: Float, val y: Float, val r: Int, val g: Int, val b: Int)
        extends GameObject with Drawable with Collision
