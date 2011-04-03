package org.frankversnel.nl.poortjes.rendering.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.rendering.Renderer;

public abstract class Drawable extends Component {
	
	public Drawable(GameObject gameObject) {
		super(gameObject);
	}

	public abstract void draw(Renderer renderer);

}
