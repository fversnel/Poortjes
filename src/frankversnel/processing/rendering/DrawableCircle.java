package frankversnel.processing.rendering;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.gameobject.GameObject;

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
		renderer.draw(position, size, color);
	}

}
