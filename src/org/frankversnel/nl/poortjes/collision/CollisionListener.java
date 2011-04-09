package org.frankversnel.nl.poortjes.collision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Component;

public abstract class CollisionListener extends Component implements ActionListener  {
	
	public CollisionListener(GameObject gameObject, Collidable collidable) {
		super(gameObject);
		
		collidable.addActionListener(this);
	}
	
	public abstract void collisionActionPerformed(GameObject collidedWith);
	
	@Override
	public void actionPerformed(ActionEvent collisionEvent) {
		GameObject collidedWith = ((CollisionEvent) collisionEvent)
				.getCollidedWith();
		
		collisionActionPerformed(collidedWith);
	}
	
	public void remove() {
		// Do nothing.
	}

}
