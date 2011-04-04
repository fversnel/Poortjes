package org.frankversnel.nl.poortjes.collision;

import java.awt.event.ActionEvent;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.component.Collidable;

public class CollisionEvent extends ActionEvent {
	private static final long serialVersionUID = 1007171953136454527L;
	
	private Collidable collidedWith;

	public CollisionEvent(Collidable source, Collidable collidedWith) {
		super(source, ActionEvent.ACTION_PERFORMED, "collision event");
		
		this.collidedWith = collidedWith;
	}
	
	public GameObject getCollidedWith() {
		return this.collidedWith.getGameObject();
	}
	
}
