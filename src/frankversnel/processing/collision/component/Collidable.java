package frankversnel.processing.collision.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Component;

public abstract class Collidable extends Component {

	public Collidable(GameObject gameObject) {
		super(gameObject);
	}

	public abstract boolean collidesWith(Collidable other);

}
