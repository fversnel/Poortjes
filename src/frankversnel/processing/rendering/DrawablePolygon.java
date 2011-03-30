package frankversnel.processing.rendering;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.Position;
import frankversnel.processing.gameobject.GameObject;

public class DrawablePolygon extends Component implements Drawable {
	private Color color;
	private Position position;
	private Polygon shape;
	
	public DrawablePolygon(GameObject gameObject, Position position, 
			Polygon polygon, Color color) {
		super(gameObject);
		
		this.shape = polygon;
		this.position = position;
		this.color = color;
	}

	public Position getPosition() {
		return position;
	}
	
	public Polygon getShape() {
		return shape;
	}
	
	public void draw(Renderer renderer) {
		renderer.draw(position, shape, color);
	}

}
