package frankversnel.processing.rendering;

import frankversnel.processing.component.Transform;
import frankversnel.processing.rendering.component.Color;

public interface Renderer {
	
	public void drawCircle(Transform transform, Color color);
	
	public void drawShape(Transform transform, String shapeId);

}
