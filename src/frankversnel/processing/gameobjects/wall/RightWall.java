package frankversnel.processing.gameobjects.wall;

import frankversnel.processing.gameobjects.Position;

public class RightWall extends Wall {
	
	public RightWall(final int screenWidth, final int screenHeight) {
		super(new Position(screenWidth - Wall.THICKNESS, 0), Wall.THICKNESS, screenHeight);
	}

}
