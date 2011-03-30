package frankversnel.processing.gameobject;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.Color;
import frankversnel.processing.rendering.DrawablePolygon;
import frankversnel.processing.rendering.RenderManager;
import frankversnel.processing.rendering.Polygon;

public class Player extends GameObject {

	public Player(RenderManager renderManager) {
		super(renderManager);
		DrawablePolygon playerLooks = new DrawablePolygon(this,
				new Position(100, 100), Polygon.square(new Size(10,10)), Color.RED);
		renderManager.addDrawable(playerLooks);
	}

}
