package org.frankversnel.nl.poortjes.rendering.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.rendering.Renderer;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;

public class DrawableShape extends Drawable {
	private String shapeId;

	public DrawableShape(GameObject gameObject, RenderingManager renderingManager, 
			String shapeId) {
		super(gameObject, renderingManager);
		
		this.shapeId = shapeId;
	}

	@Override
	public void draw(Renderer renderer) {
		Transform transform = getGameObject().getComponent(Transform.class);
		
		if(transform != null) {
			renderer.drawShape(transform, shapeId);
		}
	}

}
