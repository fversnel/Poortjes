package frankversnel.processing.component.collision;

import frankversnel.processing.gameobjects.Position;

public class BoundingBox {
	public final Position topLeftCorner;
	public final Position bottomRightCorner;

	public BoundingBox(final Position topLeftCorner,
			final Position bottomRightCorner) {
		this.topLeftCorner = topLeftCorner;
		this.bottomRightCorner = bottomRightCorner;
	}

}
