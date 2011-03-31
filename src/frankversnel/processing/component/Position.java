package frankversnel.processing.component;

import frankversnel.processing.GameObject;

public class Position extends Component {
	private float x;
	private float y;
	
	public Position(GameObject gameObject, float x, float y) {
		super(gameObject);
		
		this.x = x;
		this.y = y;
	}
	
	public void x(float x) {
		this.x = x;
	}
	
	public void y(float y) {
		this.y = y;
	}
	
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}

}
