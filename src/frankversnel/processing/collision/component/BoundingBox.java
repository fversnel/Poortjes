package frankversnel.processing.collision.component;

import frankversnel.processing.component.Position;

class BoundingBox {
	private Position topLeft;
	private Position bottomRight;
	
	public BoundingBox(Position topLeft, Position bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public Position topLeft() {
		return this.topLeft;
	}
	
	public Position bottomRight() {
		return this.bottomRight;
	}

}
