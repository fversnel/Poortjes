package frankversnel.processing;
import processing.core.PGraphics;


public class Enemy extends GameObject {
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_HEIGHT = 20;

	public Enemy(final Position initialPosition, final int speed) {
		super(initialPosition, DEFAULT_WIDTH, DEFAULT_HEIGHT, speed);
	}

	@Override
	public void render(PGraphics renderer) {
		renderer.ellipse(this.position.x, this.position.y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public void move() {
		// TODO
	}
}
