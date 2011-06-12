package org.frankversnel.nl.poortjes.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PApplet;

class Poortjes extends PApplet {
	private val logger = LoggerFactory.getLogger(getClass);

	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	override def setup = {
		logger.info("initializing poortjes")

		size(screenWithPx, screenHeightPx);
		background(backgroundClr);
		smooth();
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.nl.poortjes.game.Poortjes"));
}
