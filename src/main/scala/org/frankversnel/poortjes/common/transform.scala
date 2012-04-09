package org.frankversnel.poortjes

import java.awt.geom.AffineTransform
import akka.stm._
import processing.core.PMatrix
import processing.core.PMatrix2D

import org.frankversnel.poortjes._;

trait Transform extends Dimension {
	private val matrixRef = Ref(new PMatrix2D)
	private var angle = 0f;

	def positionX = matrix.m02
	def positionY = matrix.m12
	def scaleX = matrix.m00
	def scaleY = matrix.m11

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
			matrix.translate(width / 2, height / 2)
			matrix.rotate(theta)
			matrix.translate(-(width / 2), -(height / 2))
		}
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
object Transform {
	def slick(t: Transform) = {
		val matrix = t.matrix
		new org.newdawn.slick.geom.Transform(
				matrix.m00, matrix.m01, matrix.m02,
				matrix.m10, matrix.m11, matrix.m12)
	}
	def java(t: Transform) = {
		val matrix = t.matrix
		new AffineTransform(
				matrix.m00, matrix.m10, matrix.m01,
				matrix.m11, matrix.m02, matrix.m12)
	}
	def processing(t: Transform) = {
		t.matrix
	}
}
