package org.frankversnel.nl.poortjes.collision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Component;
public abstract class ColliderListener extends Component implements ActionListener  {
	protected Collidable collidable;

	public ColliderListener(GameObject gameObject, Collidable collidable) {
		super(gameObject);
		
		collidable.addActionListener(this);
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent collisionEvent);

	@Override
	public void remove() {
		collidable.removeActionListener(this);
	}

}
