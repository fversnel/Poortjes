package frankversnel.processing.rendering.component;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.gameobject.GameObject;
import frankversnel.processing.rendering.Renderer;

public class DrawableShape extends Component implements Drawable {
	private String shapeId;
	
	private Position position;
	private Size size;

	public DrawableShape(GameObject gameObject, String shapeId,
			Position position, Size size) {
		super(gameObject);
		
		this.shapeId = shapeId;
		
		this.position = position;
		this.size = size;
	}

	@Override
	public void draw(Renderer renderer) {
		renderer.drawShape(position, size, shapeId);
	}

}
