package org.frankversnel.poortjes.game

import org.slf4j.scala.Logging;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.frankversnel.poortjes._;
import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.resource_loading._;

class PoortjesSlick extends BasicGame("Poortjes") {

	var renderer: Renderer = null
	var renderable: Transform with Color = null

    override def init(container: GameContainer) {
		container.getGraphics().setBackground(org.newdawn.slick.Color.black);
		renderer = new Slick2DRenderer(container.getGraphics(), new SlickShapeLoader)
		renderable = new Transform with Color {
			val dimension = Dimension(50,50)
			val color = ColorValue.red
			translate(200, 200)
			rotate(20f)
			scale(2, 2)
		}
    }

    override def update(container: GameContainer, delta: Int) {
	}

    override def render(container: GameContainer, g: Graphics) {
		renderer.drawRectangle(renderable)
	}

}
object PoortjesSlick extends App with Logging {
	logger.info("initializing Poortjes")
	val app = new AppGameContainer(new PoortjesSlick)
	app.start
}
