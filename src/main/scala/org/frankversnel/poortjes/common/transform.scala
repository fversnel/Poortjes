package org.frankversnel.poortjes

import scala.math._

trait Transform extends Dimension {
	private var matrix = Matrix2D()

	private var _rotationAngle = 0f
	def rotationAngle = _rotationAngle
	def setToRotation(theta: Float) {
		rotate(-_rotationAngle)
		rotate(theta)
	}

	def translate(x: Float, y: Float): Unit = matrix = matrix.translate(x, y)
	def scale(x: Float, y: Float): Unit = matrix = matrix.scale(x, y)
	def rotate(theta: Float): Unit = {
		_rotationAngle = (_rotationAngle + theta) % (2 * Pi).toFloat
		matrix = matrix.rotate(theta, width / 2, height / 2)
	}

	def translation = matrixStack.translation
	def matrixStack: Matrix2D = {
		return if(parent.isDefined) {
			val parentTransform = parent.get.asInstanceOf[Transform]
			parentTransform.matrixStack.concatenate(matrix)
		} else {
			matrix
		}
	}

	def angleTo(t: Transform) = atan2(t.translation._2 - translation._2,
			t.translation._1 - translation._1).toFloat

	def fetchNearestOf(transforms: List[Transform]): Option[Transform] = {
		transforms.reduceOption { (t1, t2) =>
			if(distanceTo(t1) < distanceTo(t2)) t1 else t2
		}
	}

	def distanceTo(t: Transform) = {
		val distanceX = translation._1 - t.translation._1
		val distanceY = translation._2 - t.translation._2

		// Pythagorean theorem:
		// a2 + b2 = c2
		sqrt(pow(distanceX.toDouble, 2) + pow(distanceY.toDouble, 2))
	}
}

object Transform {
	def slickMatrix(t: Transform): org.newdawn.slick.geom.Transform = {
		val matrix = t.matrixStack
		return new org.newdawn.slick.geom.Transform(
				matrix.m00, matrix.m01, matrix.m02,
				matrix.m10, matrix.m11, matrix.m12)
	}
	def javaMatrix(t: Transform) = {
		new java.awt.geom.AffineTransform(t.matrixStack.flatMatrix)
	}
	def processingMatrix(t: Transform) = {
		val matrix = t.matrixStack
		new processing.core.PMatrix2D(
				matrix.m00, matrix.m01, matrix.m02,
				matrix.m10, matrix.m11, matrix.m12)
	}

	private implicit def doubleToFloat(x: Double): Float = x.toFloat
}
