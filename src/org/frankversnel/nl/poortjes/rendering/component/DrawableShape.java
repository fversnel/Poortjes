package org.frankversnel.nl.poortjes.rendering.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.rendering.Renderer;

public class DrawableShape extends Drawable {
	private String shapeId;

	public DrawableShape(GameObject gameObject, String shapeId) {
		super(gameObject);
		
		this.shapeId = shapeId;
	}

	@Override
	public void draw(Renderer renderer) {
		Transform gameObjectTransform = 
			getGameObject().safe_getComponent(Transform.class);
		renderer.drawShape(gameObjectTransform, shapeId);
	}

}
