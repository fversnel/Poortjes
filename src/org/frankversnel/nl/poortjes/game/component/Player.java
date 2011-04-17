package org.frankversnel.nl.poortjes.game.component;


import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.Collision;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Component;

public class Player extends Component implements CollisionListener {
	public Player(GameObject gameObject, Collidable collidable) {
		super(gameObject);
		
		collidable.addListener(this);
	}

	@Override
	public void collision(Collision collision) {
		Candy candy = collision.getCollidedWith().getComponent(Candy.class);
		if (candy != null) {
			collision.getCollidedWith().destroy();
		}
		
	}

}
