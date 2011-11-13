package org.frankversnel.poortjes

class Speed(val distanceInMs: Float, val rotationInMs: Float) {

	private var currentDistance = 0f
	private var currentRotation = 0f

    def move(amount: Float) {
		currentDistance = distanceInMs * amount;
	}

    def rotate(amount: Float) {
        currentRotation = rotationInMs * amount;
    }

    def getDistance = currentDistance
    def getRotation = currentRotation
}

