package org.frankversnel.nl.poortjes.dummy;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.movement.Moveable;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.DrawableShape;
import org.frankversnel.nl.poortjes.collision.CollisionManager;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;

public class PlayerWithShape extends GameObject {

	public PlayerWithShape(RenderingManager renderManager,	CollisionManager collisionManager,
			GameLoop gameLoop, float positionX, float positionY, String shapeId) {
		super(renderManager);

		new Transform(this, positionX, positionY, 20, 20, 0);
		new Moveable(this, gameLoop);
		new Speed(this, 0.001f, 0.001f);
		collisionManager.addComponent(new CollidableBox(this));

		DrawableShape playerLooks = new DrawableShape(this, shapeId);
		renderManager.addComponent(playerLooks);
	}


}
