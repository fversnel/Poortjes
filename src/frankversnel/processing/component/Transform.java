package frankversnel.processing.component;

import processing.core.PMatrix;
import processing.core.PMatrix2D;
import frankversnel.processing.GameObject;

public class Transform extends Component {
	private PMatrix2D matrix;

	public Transform(GameObject gameObject, float positionX, float positionY, 
			float scaleX, float scaleY, float rotationTheta) {
		super(gameObject);
		
		matrix = new PMatrix2D();
		matrix.translate(positionX, positionY);
		matrix.scale(scaleX, scaleY);
	}
	
	public float getPositionX() {
		return matrix.m02;
	}
	
	public float getPositionY() {
		return matrix.m12;
	}
	
	public float getScaleX() {
		return matrix.m00;
	}
	
	public float getScaleY() {
		return matrix.m11;
	}
	
	public void translate(float positionX, float positionY) {
		matrix.translate(positionX, positionY);
	}
	
	public void rotate(float theta) {
		matrix.rotate(theta);
	}

	public PMatrix getMatrix() {
		return matrix;
	}

}
