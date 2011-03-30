package frankversnel.processing.rendering.component;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.gameobject.GameObject;
import frankversnel.processing.rendering.Renderer;

public class DrawableCircle extends Component implements Drawable {
	private Position position;
	private Color color;
	private Size size;
	
	public DrawableCircle(GameObject gameObject, 
			Position position, Size size, Color color) {
		super(gameObject);
		
		this.position = position;
		this.color = color;
		this.size = size;
	}

	@Override
	public void draw(Renderer renderer) {
		renderer.drawCircle(position, size, color);
	}

}
