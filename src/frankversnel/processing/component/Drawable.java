package frankversnel.processing.component;

import frankversnel.processing.gameobject.GameObject;

public class Drawable extends Component {
	private Position position;
	private Size size;
	
	public Drawable(GameObject gameObject, Position position, Size size) {
		super(gameObject);
		
		this.size = size;
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void draw(Renderer renderer) {
		renderer.draw(position, size);
	}

}
