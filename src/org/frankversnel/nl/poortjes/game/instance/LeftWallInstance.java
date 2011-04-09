package org.frankversnel.nl.poortjes.game.instance;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;
import org.frankversnel.nl.poortjes.component.Size;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.game.component.LeftWall;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.Color;
import org.frankversnel.nl.poortjes.rendering.component.DrawableRectangle;

public class LeftWallInstance extends GameObject {

	public LeftWallInstance(RenderingManager renderManager,
			CollisionLevel collisionLevel, GameLoop gameLoop, Size size) {
		new Transform(this, 0, 0, size);
		Color.blue(this);
		new DrawableRectangle(this, renderManager);

		Collidable collidable = new CollidableBox(this, collisionLevel);
		new LeftWall(this, collidable);
	}
}
