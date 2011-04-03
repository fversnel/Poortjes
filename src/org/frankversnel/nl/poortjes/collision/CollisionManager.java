package org.frankversnel.nl.poortjes.collision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CollisionManager extends ComponentManager<Collidable> implements ActionListener {
    final static Logger logger = LoggerFactory.getLogger(CollisionManager.class);

	@Override
	public void processComponents() {
		for(Collidable collidable : components) {
			List<Collidable> otherCollidables = new LinkedList<Collidable>(components);
			otherCollidables.remove(collidable);

			for(Collidable otherCollidable : otherCollidables) {
				if(collidable.collidesWith(otherCollidable)) {
					logger.info(collidable + " collides with " + otherCollidable);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		processComponents();
	}

}
