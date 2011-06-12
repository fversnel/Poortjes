package org.frankversnel.nl.poortjes.game.instance;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Size;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.rendering.component.Color;
import org.frankversnel.nl.poortjes.rendering.component.DrawableCircle;

public class SimpleCircle extends GameObject {

	public SimpleCircle(RenderingManager renderManager, GameLoop gameLoop) {
		new Transform(this, 100, 100, new Size(20, 20));
		
		Color.green(this);
		new DrawableCircle(this, renderManager);
	}

}