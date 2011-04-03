package org.frankversnel.nl.poortjes.rendering.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.rendering.Renderer;

public class DrawableCircle extends Drawable {
	
	public DrawableCircle(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void draw(Renderer renderer) {
		GameObject gameObject = getGameObject();
		
		Transform gameObjectTransform = gameObject.safe_getComponent(Transform.class);
		Color gameObjectColor = gameObject.safe_getComponent(Color.class);
		
		renderer.drawCircle(gameObjectTransform, gameObjectColor);
	}

}
