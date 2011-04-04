package org.frankversnel.nl.poortjes.rendering.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.rendering.Renderer;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;

public abstract class Drawable extends Component {
	private RenderingManager renderingManager;
	
	public Drawable(GameObject gameObject, RenderingManager renderingManager) {
		super(gameObject);
		
		this.renderingManager = renderingManager;
		renderingManager.addComponent(this);
	}

	public abstract void draw(Renderer renderer);
	
	public void remove() {
		renderingManager.removeComponent(this);
	}

}
