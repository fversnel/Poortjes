package frankversnel.processing.gameobject;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.Color;
import frankversnel.processing.rendering.component.DrawablePolygon;

public class Player extends GameObject {

	public Player(RenderingManager renderManager) {
		super(renderManager);
		
		Position position = new Position(this, 100, 100);
		addComponent(position);
		
		Color color = Color.red(this);
		addComponent(color);
		
		Size size = new Size(this, 20, 20);
		addComponent(size);
		
		DrawablePolygon playerLooks = DrawablePolygon.square(this, size);
		renderManager.addDrawable(playerLooks);
	}

}
