package org.frankversnel.nl.poortjes.dummy;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.movement.Moveable;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.Color;
import org.frankversnel.nl.poortjes.rendering.component.DrawableCircle;

public class PlayerWithCircle extends GameObject {

	public PlayerWithCircle(RenderingManager renderManager, GameLoop gameLoop) {
		super(renderManager);
		
		new Transform(this, 200, 200, 50, 50, 0);
		Color.red(this);
		Speed speed = new Speed(this, 0.002f, 0.002f);
		speed.move(1);
		speed.rotate(1);
		new Moveable(this, gameLoop);
		
		DrawableCircle playerLooks = new DrawableCircle(this);
		renderManager.addComponent(playerLooks);
	}

}
