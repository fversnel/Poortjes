package frankversnel.processing.component;

import frankversnel.processing.GameObject;

/**
 * Holds an angle in both degrees and radians.
 * 
 * @author Frank Versnel
 */
public class Angle extends Component {
	private float angleDegrees;
	private float angleRadians;

	public Angle(GameObject gameObject, float angleDegrees) {
		super(gameObject);
		
		this.angleDegrees = angleDegrees;
		this.angleRadians = (float)Math.toRadians(this.angleDegrees);
	}

	public float getDegrees() {
		return this.angleDegrees;
	}


	public float getRadians() {
		return this.angleRadians;
	}

}
