package frankversnel.processing.collision.component;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;

public class CollidableBox extends Collidable {
	
	public CollidableBox(GameObject gameObject) {
		super(gameObject);
	}

	public boolean collidesWith(Collidable other) {
		boolean result = true;

		BoundingBox thisBox = getBoundingBox(this);
		BoundingBox otherBox = getBoundingBox(other);

		// 2D collision detection algorithm by Mary Rose Cook.
		// (http://www.maryrosecook.com/post/how-to-do-2d-collision-detection)
		//If a's bottom right x coordinate is less than b's top left x coordinate
		//    There is no collision
		//If a's top left x is greater than b's bottom right x
		//    There is no collision
		//If a's top left y is greater than b's bottom right y
		//    There is no collision
		//If a's bottom right y is less than b's top left y
		//    There is no collision
		if(thisBox.bottomRight().x() < otherBox.topLeft().x()) {
			result = false;
		}
		if(thisBox.topLeft().x() > otherBox.bottomRight().x()) {
			result = false;
		}
		if(thisBox.topLeft().y() > otherBox.bottomRight().y()) {
			result = false;
		}
		if(thisBox.bottomRight().y() < otherBox.topLeft().y()) {
			result = false;
		}

		return result;
	}

	private BoundingBox getBoundingBox(Collidable collidable) {
		GameObject collidableObject = collidable.getGameObject();
		Position position = collidableObject.safe_getComponent(Position.class);
		Size size = collidableObject.safe_getComponent(Size.class);
		
		return new BoundingBox(
				new Position(collidableObject, position.x(), position.y()),
				new Position(collidableObject, position.x() + size.width(), position.y() + size.height())
				);
	}
	
}
