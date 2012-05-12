package org.frankversnel.poortjes.game

import org.slf4j.scala.Logging
import org.newdawn.slick.BasicGame
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics

import org.frankversnel.poortjes._
import org.frankversnel.poortjes.rendering._
import org.frankversnel.poortjes.resource_loading._
import org.frankversnel.poortjes.input._
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.util.DeltaTime
import org.frankversnel.poortjes.game.gameobjects._

class PoortjesSlick extends BasicGame("Poortjes") {

	private var renderer: Renderer = null
	private var renderable: Transform with Color = null

	private var resourceLoader: SlickImageLoader = null
	private var newPlayer: Player with SlickKeyboardAdapter = null

    override def init(container: GameContainer) {
		container.getGraphics().setBackground(org.newdawn.slick.Color.black);
		resourceLoader = new SlickImageLoader
		renderer = new Slick2DRenderer(container.getGraphics(), resourceLoader)

        EntityManager.initialize(renderer)

		newPlayer = new Player(resourceLoader) with SlickKeyboardAdapter {
			//speed
			override val distanceInMs = 0.09f
			override val rotationInMs = 0.010f

			val shape = resourceLoader.addResource("ship-green.svg")

			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(150, 150)
		container.getInput().addKeyListener(newPlayer)
		EntityManager().spawn(newPlayer)
    }

    override def update(container: GameContainer, delta: Int): Unit = {
		EntityManager().process(DeltaTime(delta))
		EntityManager().cleanUp
	}

    override def render(container: GameContainer, g: Graphics): Unit = {
	}

}
object PoortjesSlick extends App with Logging {
	logger.info("initializing Poortjes")
	val app = new AppGameContainer(new PoortjesSlick)
	app.start
}
