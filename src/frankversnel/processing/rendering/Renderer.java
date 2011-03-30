package frankversnel.processing.rendering;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.rendering.component.Color;

public interface Renderer {
	
	public void drawPolygon(Position position, Color color, Position[] pologonCoords);
	
	public void drawCircle(Position position, Size size, Color color);
	
	public void drawShape(Position position, Size size, String shapeId);

}
