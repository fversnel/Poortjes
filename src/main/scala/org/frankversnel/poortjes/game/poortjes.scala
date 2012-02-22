package org.frankversnel.poortjes.game;

import akka.actor.Actor._
import akka.actor.ActorRef
import akka.util.duration._
import org.slf4j.scala.Logging;
import processing.core.PApplet;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.ComponentManager._;
import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._;
import org.frankversnel.poortjes.input._;
import org.frankversnel.poortjes.resource_loading._;

class Poortjes extends PApplet with Logging {
	private val screenWithPx = 400;
	private val screenHeightPx = 400;
	private val backgroundClr = 0;

	private var newPlayer: Player = null
	private var playerTwo: Player = null
	private var gate: Gate = null

	private var renderer: Renderer = null
	private var resourceLoader: ProcessingShapeLoader = null

	override def setup = {
		logger.info("initializing Poortjes")

		size(screenWithPx, screenHeightPx)
		background(backgroundClr)

		resourceLoader = new ProcessingShapeLoader(this)
		renderer = new Processing2DRenderer(g, resourceLoader, backgroundClr)
        EntityManager.initialize(renderer)

		val newPlayerResource = resourceLoader.addResource("ship-red.svg")
		newPlayer = new Player(newPlayerResource) {
			val dimension = Dimension(20, 20)
			//speed
			val distanceInMs = 3f
			val rotationInMs = 0.10f

			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(screenWithPx / 3, screenHeightPx / 3)
		addKeyListener(newPlayer)
		EntityManager().spawn(newPlayer)

        //playerTwo = new Player(resourceLoader) {
		//	//speed
		//	val distanceInMs = 3f
		//	val rotationInMs = 0.10f

		//	val keybindings = KeyboardBindings('i', 'k', 'j', 'l')
        //}
		//addKeyListener(playerTwo)
		//EntityManager().spawn(playerTwo)

		//val shepherd = new Shepherd(resourceLoader)
		//shepherd.translate(screenWithPx / 2, screenHeightPx / 2)
		//EntityManager().spawn(shepherd)

        //val newGate = Gate.build(resourceLoader)
        //newGate.foreach(EntityManager().spawn(_))
        //gate = newGate.head.asInstanceOf[Gate]
	}

	override def draw = {
		renderer.drawCircle(new Transform with Color {
			val dimension = Dimension(50,50)
			val color = ColorValue.red
			translate(50,50)
		})
		newPlayer.draw(renderer)
		//newPlayer.move
		//playerTwo.move
        //gate.move

        //EntityManager().process
	}
}

object Poortjes extends App {
	PApplet.main(Array("org.frankversnel.poortjes.game.Poortjes"));
}
