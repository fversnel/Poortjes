package org.frankversnel.poortjes;

import java.awt.geom.AffineTransform;
import akka.stm._
import processing.core.PMatrix;
import processing.core.PMatrix2D;

import org.frankversnel.poortjes._;

trait Transform extends Component {
	val dimension: Dimension

	private val matrixRef = Ref(new PMatrix2D)

	def positionX = atomic(matrix.m02)
	def positionY = atomic(matrix.m12)
	def scaleX = atomic(matrix.m00)
	def scaleY = atomic(matrix.m11)
	def width = dimension.width
	def height = dimension.height

	def translate(x: Float, y: Float) = atomic {
		alterMatrix(_.translate(x, y))
	}
	def scale(x: Float, y: Float) = atomic {
		alterMatrix(_.scale(x, y))
	}
	def rotate(theta: Float) = atomic {
			alterMatrix { matrix =>
			// To rotate around its own axis we need to put it in local space then rotate and put it
			// back in global space.
			matrix.translate(dimension.width / 2, dimension.height / 2)
			matrix.rotate(theta)
			matrix.translate(-(dimension.width / 2), -(dimension.height / 2))
		}
	}

	def processingMatrix = matrix
	def affineTransform(): AffineTransform = {
		return new AffineTransform(
				matrix.m00, matrix.m10, matrix.m01,
				matrix.m11, matrix.m02, matrix.m12)
	}

	private def matrix = matrixRef.get
	private def alterMatrix(matrixFunction: PMatrix2D => Unit) = atomic {
		matrixRef alter { matrix =>
			val newMatrix = matrix.get
			matrixFunction(newMatrix)
			newMatrix
		}
	}
}
