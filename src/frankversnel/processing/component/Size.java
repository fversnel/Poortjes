package frankversnel.processing.component;

import frankversnel.processing.gameobject.GameObject;

public class Size extends Component {
	private int width;
	private int height;

	public Size(GameObject gameObject, int width, int height) {
		super(gameObject);
		
		this.width = width;
		this.height = height;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

}
