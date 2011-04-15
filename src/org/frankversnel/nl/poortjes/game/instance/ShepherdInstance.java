package org.frankversnel.nl.poortjes.game.instance;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;
import org.frankversnel.nl.poortjes.component.Size;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.game.component.ai.Shepherd;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.movement.Moveable;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.DrawableShape;
import org.frankversnel.nl.poortjes.resourceloading.ResourceLoader;

public class ShepherdInstance extends GameObject {

	public ShepherdInstance(RenderingManager renderManager,
			ResourceLoader resourceloader, String resourceFile,
			CollisionLevel collisionLevel, GameLoop gameLoop, float positionX,
			float positionY) {
		new Transform(this, positionX, positionY, new Size(20, 20));
		new Moveable(this, gameLoop);
		new Speed(this, 0.25f, 0.005f);

		resourceloader.addResource(resourceFile);
		new DrawableShape(this, renderManager, resourceFile);

		Collidable collidable = new CollidableBox(this, collisionLevel);
		new Shepherd(this, collidable);
	}

}
