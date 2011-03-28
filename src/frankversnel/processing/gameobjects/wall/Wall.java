package frankversnel.processing.gameobjects.wall;

import frankversnel.processing.Visitor;
import frankversnel.processing.gameobjects.GameObject;
import frankversnel.processing.gameobjects.Position;
import frankversnel.processing.util.Color;
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

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
