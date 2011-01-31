package frankversnel.processing;

import java.util.LinkedList;
import java.util.List;

import processing.core.*;

public abstract class GameObject {
	protected Position position;

	protected int width;
	protected int height;

	protected Color color;

	public GameObject(final Position position, final int width, final int
			height, final Color color) {
		this.position = position;

		this.width = width;
		this.height = height;

		this.color = color;
	}

	public abstract void render(final PGraphics renderer);

	public boolean collides(GameObject otherObject) {
		assert(!this.equals(otherObject)) : "Game object cannot collide with itself.";

		boolean result = true;

		CollisionBox myBox = this.getCollisionBox();
		CollisionBox otherBox = otherObject.getCollisionBox();

		// 2D collision detection algorithm by maryrosecook.
		// (http://www.maryrosecook.com/post/how-to-do-2d-collision-detection)
		//If a's bottom right x coordinate is less than b's top left x coordinate
		//    There is no collision
		//If a's top left x is greater than b's bottom right x
		//    There is no collision
		//If a's top left y is greater than b's bottom right y
		//    There is no collision
		//If a's bottom right y is less than b's top left y
		//    There is no collision
		if(myBox.getBottomRightCorner().x < otherBox.getTopLeftCorner().x) {
			result = false;
		}
		if(myBox.getTopLeftCorner().x > otherBox.getBottomRightCorner().x) {
			result = false;
		}
		if(myBox.getTopLeftCorner().y > otherBox.getBottomRightCorner().y) {
			result = false;
		}
		if(myBox.getBottomRightCorner().y < otherBox.getTopLeftCorner().y) {
			result = false;
		}

		return result;
	}

	public CollisionBox getCollisionBox() {
		return new CollisionBox(
				new Position(this.position.x, this.position.y),
				new Position(this.position.x + this.width, this.position.y),
				new Position(this.position.x, this.position.y + this.height),
				new Position(this.position.x + this.width, this.position.y + this.height)
				);
	}

}
