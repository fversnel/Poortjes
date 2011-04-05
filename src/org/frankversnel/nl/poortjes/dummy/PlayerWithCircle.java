package org.frankversnel.nl.poortjes.dummy;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.movement.Moveable;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.Color;
import org.frankversnel.nl.poortjes.rendering.component.DrawableCircle;

public class PlayerWithCircle extends GameObject {

	public PlayerWithCircle(RenderingManager renderManager,
			CollisionLevel collisionLevel, GameLoop gameLoop) {
		new Transform(this, 200, 200, 20, 20, 0);
		Color.green(this);
		new Speed(this, 0.002f, 0.005f);
		new Moveable(this, gameLoop);
		new CollidableBox(this, collisionLevel);
		new DrawableCircle(this, renderManager);
	}

}
