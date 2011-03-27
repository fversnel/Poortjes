package frankversnel.processing;

public class TopWall extends Wall {
	public TopWall(final int screenWidth, final int screenHeight) {
		super(new Position(0, 0), screenWidth, Wall.THICKNESS);
	}
}
