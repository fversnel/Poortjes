package frankversnel.processing;

public class BottomWall extends Wall {
	public BottomWall(final int screenWidth, final int screenHeight) {
		super(new Position(0, screenHeight - Wall.THICKNESS), screenWidth, Wall.THICKNESS);
	}
}
