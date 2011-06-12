package org.frankversnel.nl.poortjes.game.instance;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;
import org.frankversnel.nl.poortjes.component.Size;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.game.component.Candy;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.DrawableShape;
import org.frankversnel.nl.poortjes.resourceloading.ResourceLoader;

public class CandyInstance extends GameObject {

	public CandyInstance(RenderingManager renderManager,
			ResourceLoader resourceloader, String resourceFile,
			CollisionLevel collisionLevel, GameLoop gameLoop, float positionX,
			float positionY) {
		new Transform(this, positionX, positionY, new Size(5, 5));
		new CollidableBox(this, collisionLevel);
		new Candy(this);

		resourceloader.addResource(resourceFile);

		DrawableShape looks = new DrawableShape(this, renderManager, resourceFile);
		renderManager.addComponent(looks);
	}

}
