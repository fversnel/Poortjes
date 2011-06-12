package org.frankversnel.nl.poortjes.component;

import java.awt.geom.AffineTransform;

import org.frankversnel.nl.poortjes.GameObject;

import processing.core.PMatrix;
import processing.core.PMatrix2D;

public class Transform extends Component {
	private PMatrix2D matrix;

	private Size size;

	public Transform(GameObject gameObject, float positionX, float positionY,
			Size size) {
		super(gameObject);

		matrix = new PMatrix2D();
		matrix.translate(positionX, positionY);

		this.size = size;
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

	public float getWidth() {
		return size.width();
	}

	public float getHeight() {
		return size.height();
	}

	public void translate(float positionX, float positionY) {
		matrix.translate(positionX, positionY);
	}

	public void scale(float scaleX, float scaleY) {
		matrix.scale(scaleX, scaleY);
	}

	public void rotate(float theta) {
		PMatrix2D matrixCopy = matrix.get();

		matrixCopy.translate(size.width() / 2, size.height() / 2);
		matrixCopy.rotate(theta);
		matrixCopy.translate(-(size.width() / 2), -(size.height() / 2));

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
		// No need to remove anything.
	}

	public void setPosition(float x, float y) {
		matrix.m02 = x;
		matrix.m12 = y;
	}

}
