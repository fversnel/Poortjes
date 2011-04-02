package frankversnel.processing.rendering.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Transform;
import frankversnel.processing.rendering.Renderer;

public class DrawableCircle extends Drawable {
	
	public DrawableCircle(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void draw(Renderer renderer) {
		GameObject gameObject = getGameObject();
		
		Transform gameObjectTransform = gameObject.safe_getComponent(Transform.class);
		Color gameObjectColor = gameObject.safe_getComponent(Color.class);
		
		renderer.drawCircle(gameObjectTransform, gameObjectColor);
	}

}
