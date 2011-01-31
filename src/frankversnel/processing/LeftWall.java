package frankversnel.processing;

public class LeftWall extends Wall {
	public LeftWall(final int screenWidth, final int screenHeight) {
		super(new Position(0,0), Wall.THICKNESS, screenHeight);
	}
}
