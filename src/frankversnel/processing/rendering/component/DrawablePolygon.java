package frankversnel.processing.rendering.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.Renderer;

public class DrawablePolygon extends Drawable {
	private Position[] coords;

	public DrawablePolygon(GameObject gameObject, Position... coords) {
		super(gameObject);
		
		this.coords = coords;
	}
	
	@Override
	public void draw(Renderer renderer) {
		GameObject gameObject = getGameObject();
		
		Position gameObjectPosition = gameObject.safe_getComponent(Position.class);
		Color gameObjectColor = gameObject.safe_getComponent(Color.class);
		
		renderer.drawPolygon(gameObjectPosition, gameObjectColor, coords);
	}

	public static DrawablePolygon square(GameObject gameObject, Size size) {
		return new DrawablePolygon(gameObject,
				new Position(gameObject, 0, 0),
				new Position(gameObject, size.width(), 0),
				new Position(gameObject, size.width(), size.height()),
				new Position(gameObject, 0, size.height()));
	}

}
