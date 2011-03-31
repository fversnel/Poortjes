package frankversnel.processing.dummy;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.DrawableShape;

public class PlayerWithShape extends GameObject {
	
	public PlayerWithShape(RenderingManager renderManager, String shapeId) {
		super(renderManager);
		
		DrawableShape playerLooks = new DrawableShape(
				this, shapeId, new Position(this, 200,200), new Size(this, 50, 60));
		renderManager.addComponent(playerLooks);
	}


}
