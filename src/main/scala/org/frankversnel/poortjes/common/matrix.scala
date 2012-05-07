package org.frankversnel.poortjes

import java.awt.geom.AffineTransform

class Matrix2D private(private val matrix: AffineTransform) {
	// m00, m10, m01, m11, m02, m12
	lazy val flatMatrix: Array[Double] = {
		val flatMatrix = new Array[Double](6)
		matrix.getMatrix(flatMatrix)
		flatMatrix
	}
	lazy val m00 = flatMatrix(0)
	lazy val m10 = flatMatrix(1)
	lazy val m01 = flatMatrix(2)
	lazy val m11 = flatMatrix(3)
	lazy val m02 = flatMatrix(4)
	lazy val m12 = flatMatrix(5)
	lazy val translation = (m02.toFloat, m12.toFloat)

	def translate(x: Float, y: Float): Matrix2D = newMatrix(AffineTransform.getTranslateInstance(x, y))
	def rotate(theta: Float, anchorX: Float, anchorY: Float): Matrix2D = {
		newMatrix(AffineTransform.getRotateInstance(theta, anchorX, anchorY))
	}
	def scale(x: Float, y: Float): Matrix2D = newMatrix(AffineTransform.getScaleInstance(x, y))
	def concatenate(otherMatrix: Matrix2D): Matrix2D = newMatrix(otherMatrix.matrix)

	private def newMatrix(transformation: => AffineTransform): Matrix2D = {
		val newMatrix = matrix.clone.asInstanceOf[AffineTransform]
		newMatrix.concatenate(transformation)
		return new Matrix2D(newMatrix)
	}
	private implicit def floatToDouble(x: Float): Double = x.toDouble
}
object Matrix2D {
	def apply() = new Matrix2D(new AffineTransform)
}
