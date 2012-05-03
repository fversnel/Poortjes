package org.frankversnel.poortjes

trait Transform extends Dimension {
	private var matrix = Matrix2D()

	def translate(x: Float, y: Float): Unit = matrix = matrix.translate(x, y)
	def scale(x: Float, y: Float): Unit = matrix = matrix.scale(x, y)
	def setToScale(x: Float, y: Float): Unit = matrix = matrix.setToScale(x, y)
	def rotate(theta: Float): Unit = {
		// To rotate around its own axis we need to put it in local space then rotate and put it
		// back in global space.
		matrix = matrix.translate(width / 2, height / 2)
				.rotate(theta)
				.translate(-(width / 2), -(height / 2))
	}

	def matrixStack: Matrix2D = {
		return if(parent.isDefined) {
			val parentTransform = parent.get.asInstanceOf[Transform]
			parentTransform.matrixStack.concatenate(matrix)
		} else {
			matrix
		}
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
