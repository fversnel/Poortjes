package org.frankversnel.nl.poortjes.game.component;


import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.collision.component.Collidable;

public class Player extends CollisionListener {
	public Player(GameObject gameObject, Collidable collidable) {
		super(gameObject, collidable);
	}

	@Override
	public void collisionActionPerformed(GameObject collidedWith) {
		Candy candy = collidedWith.getComponent(Candy.class);
		if (candy != null) {
			collidedWith.destroy();
		}
	}

}
