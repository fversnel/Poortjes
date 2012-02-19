package org.frankversnel.poortjes;

import java.awt.geom.AffineTransform;
import akka.stm._
import processing.core.PMatrix;
import processing.core.PMatrix2D;

import org.frankversnel.poortjes._;

trait Transform extends Component {
	// TODO Remove dimension from this component since it does not belong here
	protected val dimension: Dimension

	private val matrixRef = Ref(new PMatrix2D)
	private var angle = 0f;

	def width = dimension.width
	def height = dimension.height
	def positionX = matrix.m02
	def positionY = matrix.m12
	def scaleX = matrix.m00
	def scaleY = matrix.m11
	def rotationAngle: Float = {
		return if(parent.isDefined) {
			angle + parent.get.asInstanceOf[Transform].rotationAngle
		} else {
			angle
		}
	}
	def rotationAngleInDegrees: Float = scala.math.toDegrees(rotationAngle.toDouble).toFloat

	def translate(x: Float, y: Float) = atomic {
		alterMatrix(_.translate(x, y))
	}
	def scale(x: Float, y: Float) = atomic {
		alterMatrix(_.scale(x, y))
	}
	def rotate(theta: Float) = atomic {
		angle = theta % (2 * scala.math.Pi).toFloat;
		//alterMatrix { matrix =>
		//	// To rotate around its own axis we need to put it in local space then rotate and put it
		//	// back in global space.
		//	matrix.translate(dimension.width / 2, dimension.height / 2)
		//	matrix.rotate(angle)
		//	matrix.translate(-(dimension.width / 2), -(dimension.height / 2))
		//}
	}

	def processingMatrix: PMatrix2D = matrix
	def affineTransform: AffineTransform = {
		return new AffineTransform(
				matrix.m00, matrix.m10, matrix.m01,
				matrix.m11, matrix.m02, matrix.m12)
	}

	private def matrix: PMatrix2D = {
		val matrixStack = getMatrix.get
		if(parent.isDefined) {
			val parentTransform = parent.get.asInstanceOf[Transform]
			matrixStack.preApply(parentTransform.matrix)
		}
		matrixStack
	}
	private def getMatrix = atomic{matrixRef.get}
	private def alterMatrix(onMatrix: PMatrix2D => Unit) = atomic {
		matrixRef alter { matrix =>
			val newMatrix = matrix.get
			onMatrix(newMatrix)
			newMatrix
		}
	}

}
