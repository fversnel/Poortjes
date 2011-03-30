package frankversnel.processing.rendering.component;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;

public class Polygon {
	private List<Position> coords = new LinkedList<Position>();

	public Polygon(Position... coords) {
		this.coords.addAll(Arrays.asList(coords));
	}

	public List<Position> getCoords() {
		return this.coords;
	}

	public static Polygon square(Size size) {
		return new Polygon(
				new Position(0, 0),
				new Position(size.width(), 0),
				new Position(size.width(), size.height()),
				new Position(0, size.height()));
	}

}
