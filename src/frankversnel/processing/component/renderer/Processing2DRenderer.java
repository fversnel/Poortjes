package frankversnel.processing.component.renderer;

import processing.core.*;

import frankversnel.processing.component.PositionComponent;
import frankversnel.processing.component.SizeComponent;


public class Processing2DRenderer extends Renderer {
	private PGraphics graphics;
	
	public Processing2DRenderer(PGraphics graphics) {
		this.graphics = graphics;
	}
	
	public void draw(PositionComponent position, SizeComponent size) {
		graphics.fill(125, 125, 125);
		graphics.rect(position.x, position.y, size.getWidth(), size.getHeight());
	}

}
