package frankversnel.processing.gameobject;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.Color;
import frankversnel.processing.rendering.component.DrawablePolygon;
import frankversnel.processing.rendering.component.Polygon;

public class Player extends GameObject {

	public Player(RenderingManager renderManager) {
		super(renderManager);
		DrawablePolygon playerLooks = new DrawablePolygon(this,
				new Position(100, 100), Polygon.square(new Size(10,10)), Color.RED);
		renderManager.addDrawable(playerLooks);
	}

}
