package org.frankversnel.nl.poortjes.component;

import java.awt.geom.AffineTransform;

import org.frankversnel.nl.poortjes.GameObject;

import processing.core.PMatrix;
import processing.core.PMatrix2D;

public class Transform extends Component {
	private PMatrix2D matrix;

	public Transform(GameObject gameObject, float positionX, float positionY,
			float scaleX, float scaleY, float rotationTheta) {
		super(gameObject);

		matrix = new PMatrix2D();
		matrix.translate(positionX, positionY);
		matrix.scale(scaleX, scaleY);
		matrix.rotate(rotationTheta);
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

	/**
	 * TODO Hack to rotate around axis; apparently rotating around the 
	 * objects center works when translating by 0.5 on both axes, no idea why. Find it out!
	 * @param theta
	 */
	public void rotate(float theta) {
		PMatrix2D matrixCopy = matrix.get();
		
		matrixCopy.translate(0.5f, 0.5f);
		matrixCopy.rotate(theta);
		matrixCopy.translate(-0.5f, -0.5f);
		
		matrix.set(matrixCopy);
	}

	public AffineTransform getAffineTransform() {
		return new AffineTransform(matrix.m00, matrix.m10, matrix.m01,
				matrix.m11, matrix.m02, matrix.m12);
	}

	public PMatrix getProcessingMatrix() {
		return matrix;
	}

	@Override
	public void remove() {
	}

}
