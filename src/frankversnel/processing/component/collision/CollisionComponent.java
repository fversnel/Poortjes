package frankversnel.processing.component.collision;

import frankversnel.processing.component.Component;

public class CollisionComponent extends Component {

	/**
	 * 2D collision detection algorithm by maryrosecook.
	 * (http://www.maryrosecook.com/post/how-to-do-2d-collision-detection)
	 * If a's bottom right x coordinate is less than b's top left x coordinate
	 *   There is no collision
	 * If a's top left x is greater than b's bottom right x
	 *   There is no collision
	 * If a's top left y is greater than b's bottom right y
	 *   There is no collision
	 * If a's bottom right y is less than b's top left y
	 *   There is no collision 
	 * @param otherObject
	 * @return
	 
	public boolean collides(CollisionComponent otherObject) {
		boolean result = true;
		
		BoundingBox thisBox = this.getBoundingBox();
		BoundingBox otherBox = otherObject.getBoundingBox();


		if(thisBox.bottomRightCorner.x < otherBox.topLeftCorner.x) {
			result = false;
		}
		if(thisBox.topLeftCorner.x > otherBox.bottomRightCorner.x) {
			result = false;
		}
		if(thisBox.topLeftCorner.y > otherBox.bottomRightCorner.y) {
			result = false;
		}
		if(thisBox.bottomRightCorner.y < otherBox.topLeftCorner.y) {
			result = false;
		}
		
		if(result) {
			logger.info(this + " collides with " + otherObject);
		}

		return result;
	}
	
	private BoundingBox getBoundingBox() {
		return new BoundingBox(
				new Position(this.position.x, this.position.y),
				new Position(this.position.x + this.width, this.position.y + this.height)
				);
	}
*/
}
