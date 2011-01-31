package frankversnel.processing;

import processing.core.PGraphics;

public class Enemy extends GameObject {
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_HEIGHT = 20;

	private int speed;

	public Enemy(final Position initialPosition, final int speed, final Color color) {
		super(initialPosition, DEFAULT_WIDTH, DEFAULT_HEIGHT, color);

		this.speed = speed;
	}

	@Override
	public void render(PGraphics renderer) {
		renderer.fill(this.color.r, this.color.g, this.color.b);
		renderer.ellipse(this.position.x, this.position.y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public void move() {
		// TODO
	}
}
