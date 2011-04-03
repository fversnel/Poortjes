package org.frankversnel.nl.poortjes.dummy;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.movement.Moveable;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.DrawableShape;

public class PlayerWithShape extends GameObject {
	
	public PlayerWithShape(RenderingManager renderManager, 
			GameLoop gameLoop, String shapeId) {
		super(renderManager);
		
		new Transform(this, 300, 300, 50, 50, 0);
		new Moveable(this, gameLoop);
		new Speed(this, 0.001f, 0.001f).rotate(1);
		
		DrawableShape playerLooks = new DrawableShape(this, shapeId);
		renderManager.addComponent(playerLooks);
	}


}
