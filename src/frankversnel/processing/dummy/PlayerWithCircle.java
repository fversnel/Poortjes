package frankversnel.processing.dummy;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Speed;
import frankversnel.processing.component.Transform;
import frankversnel.processing.gameloop.GameLoop;
import frankversnel.processing.movement.Moveable;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.Color;
import frankversnel.processing.rendering.component.DrawableCircle;

public class PlayerWithCircle extends GameObject {

	public PlayerWithCircle(RenderingManager renderManager, GameLoop gameLoop) {
		super(renderManager);
		
		new Transform(this, 200, 200, 50, 50, 0);
		Color.red(this);
		new Speed(this, 0.2f, 0.2f);
		new Moveable(this, gameLoop);
		
		DrawableCircle playerLooks = new DrawableCircle(this);
		renderManager.addComponent(playerLooks);
	}

}
