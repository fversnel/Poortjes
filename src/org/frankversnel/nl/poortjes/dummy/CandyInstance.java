package org.frankversnel.nl.poortjes.dummy;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionManager;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.game.Candy;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.DrawableShape;

public class CandyInstance extends GameObject {
	
	public CandyInstance(RenderingManager renderManager, CollisionManager collisionManager,
			GameLoop gameLoop, float positionX, float positionY, String shapeId) {
		new Transform(this, positionX, positionY, 20, 20, 0);
		new CollidableBox(this, collisionManager);
		new Candy(this);

		DrawableShape looks = new DrawableShape(this, renderManager, shapeId);
		renderManager.addComponent(looks);
	}

}
