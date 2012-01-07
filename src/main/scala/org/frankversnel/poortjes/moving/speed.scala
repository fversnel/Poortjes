package org.frankversnel.poortjes.moving

import org.frankversnel.poortjes._

trait Speed extends Component {
	val distanceInMs: Float
	val rotationInMs: Float

	private var currentDistance = 0f
	private var currentRotation = 0f

	def moveSpeed(amount: Float) {
        currentDistance = distanceInMs * amount
	}

	def rotationSpeed(amount: Float) {
	    currentRotation = rotationInMs * amount
	}

	def getDistance = currentDistance
	def getRotation = currentRotation
}

