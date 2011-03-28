package frankversnel.processing.gameobjects.wall;

import frankversnel.processing.Visitor;
import frankversnel.processing.gameobjects.Position;

public class TopWall extends Wall {
	
	public TopWall(final int screenWidth, final int screenHeight) {
		super(new Position(0, 0), screenWidth, Wall.THICKNESS);
	}
	

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
