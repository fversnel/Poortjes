package frankversnel.processing.component;

import frankversnel.processing.GameObject;

public class Speed extends Component {
	private float distanceInMetersPerSecond;
	private float rotationSpeed; 

	public Speed(GameObject gameObject, float distanceInMetersPerSecond,
			float rotationSpeed) {
		super(gameObject);
		
		this.distanceInMetersPerSecond = distanceInMetersPerSecond;
		this.rotationSpeed = rotationSpeed;
	}
	
	public float getDistance() {
		return this.distanceInMetersPerSecond;
	}
	
	public float getRotation() {
		return this.rotationSpeed;
	}

}
