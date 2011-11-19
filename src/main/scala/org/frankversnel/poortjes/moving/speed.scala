package org.frankversnel.poortjes.moving

import akka.stm._

import org.frankversnel.poortjes._

trait Speed extends Component {
	val distanceInMs: Float
	val rotationInMs: Float

	private val currentDistance = Ref(0f)
	private val currentRotation = Ref(0f)

	def moveSpeed(amount: Float) {
		atomic {
			currentDistance.set(distanceInMs * amount)
		}
	}

	def rotationSpeed(amount: Float) {
		atomic {
			currentRotation.set(rotationInMs * amount)
		}
	}

	def getDistance = atomic(currentDistance.get)
	def getRotation = atomic(currentRotation.get)
}

