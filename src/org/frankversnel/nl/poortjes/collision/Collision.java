package org.frankversnel.nl.poortjes.collision;

import java.util.EventObject;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.component.Collidable;

public class Collision extends EventObject {
	private static final long serialVersionUID = 1007171953136454527L;

	private Collidable collidedWith;

	public Collision(Collidable source, Collidable collidedWith) {
		super(source);

		this.collidedWith = collidedWith;
	}

	public GameObject getCollidedWith() {
		return this.collidedWith.getGameObject();
	}

}
