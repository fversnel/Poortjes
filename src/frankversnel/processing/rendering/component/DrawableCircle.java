package frankversnel.processing.rendering.component;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.gameobject.GameObject;
import frankversnel.processing.rendering.Renderer;

public class DrawableCircle extends Component implements Drawable {
	
	public DrawableCircle(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void draw(Renderer renderer) {
		GameObject gameObject = getGameObject();
		
		Position gameObjectPosition = gameObject.getComponent(Position.class);
		Size gameObjectSize = gameObject.getComponent(Size.class);
		Color gameObjectColor = gameObject.getComponent(Color.class);
		
		renderer.drawCircle(gameObjectPosition, gameObjectSize, gameObjectColor);
	}

}
