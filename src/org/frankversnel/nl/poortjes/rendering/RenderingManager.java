package org.frankversnel.nl.poortjes.rendering;

import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.rendering.component.Drawable;

public class RenderingManager extends ComponentManager<Drawable> {
	private Renderer renderer;

	public RenderingManager(Renderer renderer, GameLoop gameLoop) {
		super(gameLoop);

		this.renderer = renderer;
	}

	@Override
	public void processComponents() {
		renderer.clearScreen();
		for (Drawable drawable : getComponents()) {
			drawable.draw(renderer);
		}
	}

}
