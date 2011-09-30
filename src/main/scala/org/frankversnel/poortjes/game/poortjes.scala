package org.frankversnel.poortjes.game;

import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes._;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	override def setup = {
		logger.info("initializing poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)
		smooth()

    val renderer = new Processing2DRenderer(g)

    val newPlayer = new Player(new Color(255, 0, 0), new Position(0, 0))
    newPlayer.draw(renderer)
  }
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.nl.poortjes.game.Poortjes"));
}
