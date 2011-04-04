package org.frankversnel.nl.poortjes.rendering.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.rendering.Renderer;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;

public class DrawableCircle extends Drawable {

	public DrawableCircle(GameObject gameObject,
			RenderingManager renderingManager) {
		super(gameObject, renderingManager);
	}

	@Override
	public void draw(Renderer renderer) {
		GameObject gameObject = getGameObject();

		Transform transform = gameObject.getComponent(Transform.class);
		Color color = gameObject.getComponent(Color.class);

		if (transform != null && color != null) {
			renderer.drawCircle(transform, color);
		}
	}

}
