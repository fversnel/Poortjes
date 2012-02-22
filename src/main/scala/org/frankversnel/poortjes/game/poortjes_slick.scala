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
	private var newPlayer: Player = null

    override def init(container: GameContainer) {
		container.getGraphics().setBackground(org.newdawn.slick.Color.black);
		resourceLoader = new SlickImageLoader
		renderer = new Slick2DRenderer(container.getGraphics(), resourceLoader)

        EntityManager.initialize(renderer)

		val newPlayerResource = resourceLoader.addResource("ship-red.png")
		newPlayer = new Player(newPlayerResource) {
			val dimension = Dimension(20, 20)
			//speed
			val distanceInMs = 3f
			val rotationInMs = 0.10f

			val keybindings = KeyboardBindings('w', 's', 'a', 'd')
		}
		newPlayer.translate(150, 150)
		EntityManager().spawn(newPlayer)

		renderable = new GameObject with Drawable with Transform with Color {
			val dimension = Dimension(50,50)
			val color = ColorValue.red
			translate(200, 200)
			rotate(20f)
			scale(2, 2)

			override def draw(renderer: Renderer) {
				renderer.drawCircle(this)
			}
		}
		EntityManager().spawn(renderable)
    }

    override def update(container: GameContainer, delta: Int) {
	}

    override def render(container: GameContainer, g: Graphics) {
		EntityManager().process
	}

}
object PoortjesSlick extends App with Logging {
	logger.info("initializing Poortjes")
	val app = new AppGameContainer(new PoortjesSlick)
	app.start
}
