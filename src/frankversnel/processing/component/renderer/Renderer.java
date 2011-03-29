package frankversnel.processing.component.renderer;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.PositionComponent;
import frankversnel.processing.component.SizeComponent;

public abstract class Renderer extends Component {
	
	public abstract void draw(PositionComponent position, SizeComponent size);
	
}
