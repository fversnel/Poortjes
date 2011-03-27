package frankversnel.processing;

public class RightWall extends Wall {
	public RightWall(final int screenWidth, final int screenHeight) {
		super(new Position(screenWidth - Wall.THICKNESS, 0), Wall.THICKNESS, screenHeight);
	}
}
