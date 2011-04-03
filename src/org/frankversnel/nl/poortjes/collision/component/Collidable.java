package org.frankversnel.nl.poortjes.collision.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;

public abstract class Collidable extends Component {

	public Collidable(GameObject gameObject) {
		super(gameObject);
	}

	public abstract boolean collidesWith(Collidable other);

}
