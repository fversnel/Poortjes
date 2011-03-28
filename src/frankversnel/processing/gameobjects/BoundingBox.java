package frankversnel.processing.gameobjects;

public class BoundingBox {
	public final Position topLeftCorner;
	public final Position bottomRightCorner;

	public BoundingBox(final Position topLeftCorner,
			final Position bottomRightCorner) {
		this.topLeftCorner = topLeftCorner;
		this.bottomRightCorner = bottomRightCorner;
	}

}
