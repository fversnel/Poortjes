package frankversnel.processing.dummy;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Speed;
import frankversnel.processing.component.Transform;
import frankversnel.processing.gameloop.GameLoop;
import frankversnel.processing.movement.Moveable;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.DrawableShape;

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
