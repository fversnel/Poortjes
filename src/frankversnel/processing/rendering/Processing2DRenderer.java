package frankversnel.processing.rendering;

import frankversnel.processing.component.Transform;
import frankversnel.processing.rendering.component.Color;
import frankversnel.processing.resourceloading.ProcessingShapeLoader;
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
	public void drawCircle(Transform transform, Color color) {
		fill(color);
		
		graphics.setMatrix(transform.getMatrix());
		graphics.pushMatrix();
		// TODO Drawing with a scale of 1 results in an empty screen, why?
		graphics.ellipse(0, 0, 2, 2);
		graphics.popMatrix();
	}
	
	private void fill(Color color) {
		graphics.fill(color.r(), color.g(), color.b());
	}

	@Override
	public void drawShape(Transform transform, String shapeId) {
		graphics.setMatrix(transform.getMatrix());
		graphics.pushMatrix();
		graphics.shape(shapeLoader.getResource(shapeId), 0, 0, 2, 2);
		graphics.popMatrix();
	}

}
