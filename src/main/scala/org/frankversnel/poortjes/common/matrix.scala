package org.frankversnel.poortjes

import java.awt.geom.AffineTransform

class Matrix2D private(private val matrix: AffineTransform) {
	// m00, m10, m01, m11, m02, m12
	lazy val flatMatrix: Array[Double] = {
		val flatMatrix = new Array[Double](6)
		matrix.getMatrix(flatMatrix)
		flatMatrix
	}
	val m00 = flatMatrix(0)
	val m10 = flatMatrix(1)
	val m01 = flatMatrix(2)
	val m11 = flatMatrix(3)
	val m02 = flatMatrix(4)
	val m12 = flatMatrix(5)
	val translation = (m02, m12)

	def translate(x: Float, y: Float): Matrix2D = newMatrix(AffineTransform.getTranslateInstance(x, y))
	def rotate(theta: Float): Matrix2D = newMatrix(AffineTransform.getRotateInstance(theta))
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
