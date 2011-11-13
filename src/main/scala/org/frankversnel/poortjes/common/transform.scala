package org.frankversnel.poortjes;

import java.awt.geom.AffineTransform;
import processing.core.PMatrix;
import processing.core.PMatrix2D;

import org.frankversnel.poortjes._;

class Transform(private val dimension: Dimension) {

	private val matrix = new PMatrix2D;

	def positionX = matrix.m02
	def positionY = matrix.m12
	def scaleX = matrix.m00
	def scaleY = matrix.m11
	def width = dimension.width
	def height = dimension.height

	def translate(x: Float, y: Float) = matrix.translate(x, y)
	def scale(x: Float, y: Float) = matrix.scale(x, y)

	def rotate(theta: Float) {
		val matrixCopy = matrix.get

		// To rotate around its own axis we need to put it in local space then rotate and put it
		// back in global space.
		matrixCopy.translate(dimension.width / 2, dimension.height / 2)
		matrixCopy.rotate(theta)
		matrixCopy.translate(-(dimension.width / 2), -(dimension.height / 2))

		matrix.set(matrixCopy);
	}

	def affineTransform(): AffineTransform = {
		return new AffineTransform(
				matrix.m00, matrix.m10, matrix.m01,
				matrix.m11, matrix.m02, matrix.m12)
	}

	def processingMatrix = matrix

}
