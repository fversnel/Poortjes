package frankversnel.processing.rendering;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;

public interface Renderer {
	
	public void draw(Position position, Polygon shape, Color color);
	
	public void draw(Position position, Size size, Color color);
	
	public void draw(Position position, Size size, String shapeId);

}
