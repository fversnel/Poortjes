package org.frankversnel.nl.poortjes.game;

import java.awt.event.ActionEvent;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.ColliderListener;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.component.Collidable;

public class Shepherd extends ColliderListener {
	public Shepherd(GameObject gameObject, Collidable collidable) {
		super(gameObject, collidable);
	}
	
	@Override
	public void actionPerformed(ActionEvent collisionEvent) {
		GameObject collidedWith = ((CollisionEvent) collisionEvent).getCollidedWith();

		Player player = collidedWith.getComponent(Player.class);
		if (player != null) {
			collidedWith.destroy();
		}
	}

}
