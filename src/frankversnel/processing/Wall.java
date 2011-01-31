package frankversnel.processing;

import processing.core.PGraphics;

public abstract class Wall extends GameObject {
	protected static int THICKNESS = 5;
	protected static Color COLOR = Color.BLUE;

	public Wall(final Position position, final int width, final int height) {
		super(position, width, height, COLOR);
	}

	@Override
	public void render(PGraphics renderer) {
		renderer.fill(this.color.r, this.color.g, this.color.b);
		renderer.rect(this.position.x, this.position.y,
				this.width, this.height);
	}
}
