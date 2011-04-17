package org.frankversnel.nl.poortjes.rendering;

import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameTick;
import org.frankversnel.nl.poortjes.gameloop.GameTickListener;
import org.frankversnel.nl.poortjes.rendering.component.Drawable;

public class RenderingManager extends ComponentManager<Drawable> implements GameTickListener {
	private Renderer renderer;

	public RenderingManager(Renderer renderer, GameLoop gameLoop) {
		this.renderer = renderer;
		
		gameLoop.addListener(this);
	}

	@Override
	public void processComponents() {
		renderer.clearScreen();
		for (Drawable drawable : getComponents()) {
			drawable.draw(renderer);
		}
	}

	@Override
	public void gameTickOccurred(GameTick gameTick) {
		processComponents();
	}

}
