package frankversnel.processing.gameobjects.gate;

import java.util.LinkedList;
import java.util.List;

import processing.core.PGraphics;
import frankversnel.processing.Visitor;
import frankversnel.processing.gameobjects.GameObject;
import frankversnel.processing.gameobjects.Position;
import frankversnel.processing.util.Color;

public class Gate extends GameObject {
	private List<GameObject> parts;

	public Gate(Position position, int width, int height, Color color) {
		super(position, width, height, color);
		
		parts = new LinkedList<GameObject>();
		parts.add(new Stick(position, width, height, color));
		parts.add(new Knob(position, width, height, color));
		parts.add(new Knob(position, width, height, color));
	}
	
	@Override
	public boolean collides(GameObject otherObject) {
		boolean collision = false;
		
		for(GameObject part : parts) {
			collision = part.collides(otherObject);
		}
		
		return collision;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public void render(PGraphics renderer) {
		// TODO Auto-generated method stub
		
	}

}
