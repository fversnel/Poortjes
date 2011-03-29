package frankversnel.processing.gameobject;

import frankversnel.processing.component.Drawable;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.component.manager.RenderManager;

public class Player extends GameObject {
	
	public Player(RenderManager renderManager) {
		super(renderManager);
		Drawable playerLooks = new Drawable(this, new Position(this, 100, 100), new Size(this, 50, 50)); 
		renderManager.addDrawable(playerLooks);
	}

}
