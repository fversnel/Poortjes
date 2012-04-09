package org.frankversnel.poortjes.game

import org.slf4j.scala.Logging;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.resource_loading._;
import org.frankversnel.poortjes.input._;
import org.frankversnel.poortjes.collision._;

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

		val newPlayerResource = resourceLoader.addResource("ship-red.png")
		newPlayer = new Player(newPlayerResource) with SlickKeyboardAdapter {
			val dimension = (20, 20)
			//speed
			val distanceInMs = 0.09f
			val rotationInMs = 0.010f

			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(150, 150)
		container.getInput().addKeyListener(newPlayer)
		EntityManager().spawn(newPlayer)

		renderable = new GameObject with Drawable with Color with Collidable {
			val dimension = (50, 50)
			val color = Color.red
			translate(200, 200)
			rotate(20f)

			def draw(renderer: Renderer): Unit = {
				renderer.drawCircle(this)
			}
		}
		EntityManager().spawn(renderable)
    }

    override def update(container: GameContainer, delta: Int): Unit = {
		newPlayer.move
	}

    override def render(container: GameContainer, g: Graphics): Unit = {
		EntityManager().process
	}

}
object PoortjesSlick extends App with Logging {
	logger.info("initializing Poortjes")
	val app = new AppGameContainer(new PoortjesSlick)
	app.start
}
