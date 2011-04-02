package frankversnel.processing.rendering.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Transform;
import frankversnel.processing.rendering.Renderer;

public class DrawableShape extends Drawable {
	private String shapeId;

	public DrawableShape(GameObject gameObject, String shapeId) {
		super(gameObject);
		
		this.shapeId = shapeId;
	}

	@Override
	public void draw(Renderer renderer) {
		Transform gameObjectTransform = 
			getGameObject().safe_getComponent(Transform.class);
		renderer.drawShape(gameObjectTransform, shapeId);
	}

}
