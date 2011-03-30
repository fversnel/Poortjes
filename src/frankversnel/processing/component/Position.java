package frankversnel.processing.component;

import frankversnel.processing.gameobject.GameObject;

public class Position extends Component {
	private int x;
	private int y;
	
	public Position(GameObject gameObject, int x, int y) {
		super(gameObject);
		
		this.x = x;
		this.y = y;
	}
	
	public void x(int x) {
		this.x = x;
	}
	
	public void y(int y) {
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}

}
