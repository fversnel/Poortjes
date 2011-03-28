package frankversnel.processing.gameobjects;

import frankversnel.processing.Visitor;
import frankversnel.processing.util.Color;
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

	private int speed;

	public Player(final Position initialPosition, final char forward, final char backward,
			final char left, final char right, final Color color) {
		super(initialPosition, DEFAULT_WIDTH, DEFAULT_HEIGHT, color);

		this.forward = forward;
		this.backward = backward;
		this.left = left;
		this.right = right;

		this.speed = DEFAULT_SPEED;
	}

	public void render(final PGraphics renderer) {
		renderer.fill(this.color.r, this.color.g, this.color.b);
		renderer.triangle(
				this.position.x, this.position.y + this.height,
				this.position.x + this.width / 2, this.position.y,
				this.position.x + this.width, this.position.y + this.height
				);
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

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
