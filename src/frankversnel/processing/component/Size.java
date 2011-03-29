package frankversnel.processing.component;

import frankversnel.processing.gameobject.GameObject;

public class Size extends Component {
	public int width;
	public int height;
	
	public Size(GameObject gameObject, int width, int height) {
		super(gameObject);
		
		this.width = width;
		this.height = height;
	}

}
