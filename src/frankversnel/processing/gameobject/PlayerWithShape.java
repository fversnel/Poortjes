package frankversnel.processing.gameobject;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.DrawableShape;
import frankversnel.processing.rendering.RenderManager;

public class PlayerWithShape extends GameObject {
	
	public PlayerWithShape(RenderManager renderManager, String shapeId) {
		super(renderManager);
		DrawableShape playerLooks = new DrawableShape(this, shapeId,
				new Position(200, 200), new Size(20, 20));
		renderManager.addDrawable(playerLooks);
	}

}
