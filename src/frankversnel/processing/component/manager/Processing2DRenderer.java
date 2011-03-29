package frankversnel.processing.component.manager;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Renderer;
import frankversnel.processing.component.Size;
import processing.core.PGraphics;

public class Processing2DRenderer implements Renderer {
	private PGraphics graphics;
	
	public Processing2DRenderer(PGraphics graphics) {
		this.graphics = graphics;
	}

	@Override
	public void draw(Position position, Size size) {
		graphics.fill(125);
		graphics.rect(position.x, position.y, size.width, size.height);
	}

}
