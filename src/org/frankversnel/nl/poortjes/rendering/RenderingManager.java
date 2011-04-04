package org.frankversnel.nl.poortjes.rendering;

import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.rendering.component.Drawable;

public class RenderingManager extends ComponentManager<Drawable> {
	private Renderer renderer;
	
	public RenderingManager(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void processComponents() {
		for(Drawable drawable : getComponents()) {
			drawable.draw(renderer);
		}
	}

}
