package frankversnel.processing.gameobjects.gate;

import processing.core.PGraphics;
import frankversnel.processing.Visitor;
import frankversnel.processing.gameobjects.GameObject;
import frankversnel.processing.gameobjects.Position;
import frankversnel.processing.util.Color;

public class Knob extends GameObject {

	public Knob(Position position, int width, int height, Color color) {
		super(position, width, height, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(PGraphics renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
