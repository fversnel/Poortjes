package frankversnel.processing.dummy;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.Color;
import frankversnel.processing.rendering.component.DrawablePolygon;

public class PlayerWithPolygon extends GameObject {

	public PlayerWithPolygon(RenderingManager renderManager) {
		super(renderManager);
		
		new Position(this, 100, 100);
		Color.red(this);
		Size size = new Size(this, 20, 20);
		
		DrawablePolygon playerLooks = DrawablePolygon.square(this, size);
		renderManager.addComponent(playerLooks);
	}

}
