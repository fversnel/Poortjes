package org.frankversnel.nl.poortjes.collision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;

public class CollisionLevel extends ComponentManager<Collidable> implements ActionListener {
	List<CollisionLevel> collisionLevels =
		new LinkedList<CollisionLevel>();
	
	public CollisionLevel(GameLoop gameLoop, CollisionLevel... collidesWith) {
		gameLoop.addActionListener(this);
		
		collisionLevels.addAll(Arrays.asList(collidesWith));
	}
	
	@Override
	public void processComponents() {
		for(Collidable ours : getComponents()) {
			for(CollisionLevel level : collisionLevels) {
				for(Collidable theirs : level.getComponents()) {
					ours.collidesWith(theirs);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		processComponents();
	}

}
