package frankversnel.processing;

public class CollisionBox {
	private Position topLeftCorner;
	private Position topRightCorner;
	private Position bottomLeftCorner;
	private Position bottomRightCorner;

	public CollisionBox(final Position topLeftCorner, final Position topRightCorner,
			final Position bottomLeftCorner, final Position bottomRightCorner) {
		this.topLeftCorner = topLeftCorner;
		this.topRightCorner = topRightCorner;
		this.bottomLeftCorner = bottomLeftCorner;
		this.bottomRightCorner = bottomRightCorner;
	}

	public Position getTopLeftCorner() {
		return this.topLeftCorner;
	}

	public Position getTopRightCorner() {
		return this.topRightCorner;
	}

	public Position getBottomLeftCorner() {
		return this.bottomLeftCorner;
	}

	public Position getBottomRightCorner() {
		return this.bottomRightCorner;
	}
}
