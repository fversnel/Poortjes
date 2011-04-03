package frankversnel.processing.component;

import frankversnel.processing.GameObject;

public class Speed extends Component {
	private final float distanceInMetersPerSecond;
	private final float rotationSpeed;
	
	private float currentDistance = 0.0f;
	private float currentRotation = 0.0f;

	public Speed(GameObject gameObject, float distanceInMetersPerSecond,
			float rotationSpeed) {
		super(gameObject);
		
		this.distanceInMetersPerSecond = distanceInMetersPerSecond;
		this.rotationSpeed = rotationSpeed;
	}
	
	public void rotate(float amount) {
		this.currentRotation = rotationSpeed * amount;
	}
	
	public void move(float amount) {
		this.currentDistance = distanceInMetersPerSecond * amount;
	}
	
	public float getDistance() {
		return this.currentDistance;
	}
	
	public float getRotation() {
		return this.currentRotation;
	}

}
