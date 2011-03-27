package frankversnel.processing;

public class LeftWall extends Wall {
	public LeftWall(final int screenWidth, final int screenHeight) {
		super(new Position(0, 0), Wall.THICKNESS, screenHeight);
	}

	@Override
	public boolean collides(GameObject otherObject) {
		boolean result = super.collides(otherObject);

		if(result) {
			// TODO Retain the other object's current position.

		}

		return result;
	}
}
