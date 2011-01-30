package frankversnel.processing;

import processing.core.*;

public class Player extends GameObject {
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_HEIGHT = 20;
	private static final int DEFAULT_SPEED = 5;

	// Controls
	private final char forward;
	private final char backward;
	private final char left;
	private final char right;

	public Player(final Position initialPosition, final char forward, final char backward,
			final char left, final char right) {
		super(initialPosition, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SPEED);

		this.forward = forward;
		this.backward = backward;
		this.left = left;
		this.right = right;
	}

	public void render(final PGraphics renderer) {
		renderer.rect(this.position.x, this.position.y, this.width, this.height);
	}

	public void handleInput(final char key) {
		if(key == this.forward) {
			this.position.y = this.position.y - speed;
		} else if(key == this.backward) {
			this.position.y = this.position.y +  speed;
		} else if(key == this.left) {
			this.position.x = this.position.x - speed;
		} else if(key == this.right) {
			this.position.x = this.position.x + speed;
		}
	}

}
