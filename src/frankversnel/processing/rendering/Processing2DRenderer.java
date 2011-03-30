package frankversnel.processing.rendering;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Processing2DRenderer implements Renderer {
	private PGraphics graphics;
	private ProcessingShapeLoader shapeLoader;

	public Processing2DRenderer(PGraphics graphics, 
			ProcessingShapeLoader shapeLoader) {
		this.graphics = graphics;
		this.shapeLoader = shapeLoader;
	}

	@Override
	public void draw(Position position, Polygon polygon, Color color) {
		fill(color);

		graphics.beginShape();
		for(Position coords : polygon.getCoords()) {
			graphics.vertex(position.x() + coords.x(),
					position.y() + coords.y());
		}
		graphics.endShape(PConstants.CLOSE);
	}

	@Override
	public void draw(Position position, Size size, Color color) {
		graphics.ellipse(position.x(), position.y(), 
				size.width(), size.height());
	}
	
	private void fill(Color color) {
		graphics.fill(color.r(), color.g(), color.b());
	}

	@Override
	public void draw(Position position, Size size, String shapeId) {
		graphics.shape(shapeLoader.getShape(shapeId), 
				position.x(), position.y(), 
				size.width(), size.height());
	}

}
