package org.frankversnel.nl.poortjes.collision;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameTick;
import org.frankversnel.nl.poortjes.gameloop.GameTickListener;

public class CollisionLevel extends ComponentManager<Collidable> implements
		GameTickListener {
	List<CollisionLevel> collisionLevels = new LinkedList<CollisionLevel>();

	public CollisionLevel(GameLoop gameLoop, CollisionLevel... collidesWith) {
		gameLoop.addListener(this);

		collisionLevels.addAll(Arrays.asList(collidesWith));
	}

	@Override
	public void processComponents() {
		for (Collidable ours : getComponents()) {
			for (CollisionLevel level : collisionLevels) {
				for (Collidable theirs : level.getComponents()) {
					ours.collidesWith(theirs);
				}
			}
		}
	}
	
	@Override
	public void gameTickOccurred(GameTick gameTick) {
		processComponents();
	}

}
