package frankversnel.processing.rendering.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.Renderer;

public class DrawableCircle extends Drawable {
	
	public DrawableCircle(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void draw(Renderer renderer) {
		GameObject gameObject = getGameObject();
		
		Position gameObjectPosition = gameObject.safe_getComponent(Position.class);
		Size gameObjectSize = gameObject.safe_getComponent(Size.class);
		Color gameObjectColor = gameObject.safe_getComponent(Color.class);
		
		renderer.drawCircle(gameObjectPosition, gameObjectSize, gameObjectColor);
	}

}
