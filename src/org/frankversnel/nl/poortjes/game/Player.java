package org.frankversnel.nl.poortjes.game;

import java.awt.event.ActionEvent;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.ColliderListener;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.component.Collidable;

public class Player extends ColliderListener {
	public Player(GameObject gameObject, Collidable collidable) {
		super(gameObject, collidable);
	}

	@Override
	public void actionPerformed(ActionEvent collisionEvent) {
		GameObject collidedWith = ((CollisionEvent) collisionEvent)
				.getCollidedWith();

		Candy candy = collidedWith.getComponent(Candy.class);
		if (candy != null) {
			collidedWith.destroy();
		}
	}

}
