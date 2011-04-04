package org.frankversnel.nl.poortjes.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Component;

public class Player extends Component implements ActionListener {
	private Collidable collidable;

	public Player(GameObject gameObject, Collidable collidable) {
		super(gameObject);

		collidable.addActionListener(this);
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

	@Override
	public void remove() {
		collidable.removeActionListener(this);
	}

}
