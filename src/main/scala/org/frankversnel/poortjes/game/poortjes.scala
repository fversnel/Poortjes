package org.frankversnel.poortjes.game;

import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.rendering.Processing2DRenderer;
import org.frankversnel.poortjes.resource_loading.ProcessingShapeLoader;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	override def setup = {
		logger.info("initializing poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)
		smooth

		val renderer = new Processing2DRenderer(g, new ProcessingShapeLoader(this), backgroundClr)

		val newPlayer = new Player(new Color(255, 0, 0), new Transform(new Dimension(50, 50)))
		newPlayer.draw(renderer)
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
