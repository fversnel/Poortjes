package org.frankversnel.nl.poortjes.collision;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;

public class CollisionLevel extends ComponentManager<Collidable> {
	List<CollisionLevel> collisionLevels = new LinkedList<CollisionLevel>();

	public CollisionLevel(GameLoop gameLoop, CollisionLevel... collidesWith) {
		super(gameLoop);

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

}
