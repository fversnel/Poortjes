package frankversnel.processing.rendering.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Component;
import frankversnel.processing.rendering.Renderer;

public abstract class Drawable extends Component {
	
	public Drawable(GameObject gameObject) {
		super(gameObject);
	}

	public abstract void draw(Renderer renderer);

}
