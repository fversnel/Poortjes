package org.frankversnel.nl.poortjes.game.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Transform;

public abstract class Wall extends CollisionListener {

	public Wall(GameObject gameObject, Collidable collidable) {
		super(gameObject, collidable);
	}

	@Override
	public void collisionActionPerformed(GameObject collidedWith) {
		Transform ownTransform = getGameObject().getComponent(Transform.class);
		Transform collidedTransform = collidedWith.getComponent(Transform.class);
		
		if(collidedTransform != null) {
			wallCollision(ownTransform, collidedTransform);
		}
	}
	
	public abstract void wallCollision(Transform ownTransform, Transform collidedTransform);

}
