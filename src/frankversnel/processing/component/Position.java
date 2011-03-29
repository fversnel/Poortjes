package frankversnel.processing.component;

import frankversnel.processing.gameobject.GameObject;

public class Position extends Component {
	public int x;
	public int y;
	
	public Position(GameObject gameObject, int x, int y) {
		super(gameObject);
		
		this.x = x;
		this.y = y;
	}

}
