package org.frankversnel.nl.poortjes.game.component;


import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Transform;

public class LeftWall extends Wall {

	public LeftWall(GameObject gameObject, Collidable collidable) {
		super(gameObject, collidable);
	}

	@Override
	public void wallCollision(Transform ownTransform,
			Transform collidedTransform) {
		if(ownTransform.getPositionX() > collidedTransform.getPositionX()) {
			collidedTransform.setPosition(ownTransform.getPositionX() + ownTransform.getWidth() + 20, 
					collidedTransform.getPositionY());
		}
	}

}
