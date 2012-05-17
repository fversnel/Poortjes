package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

class DeltaTimeTracker {
	private var oldTime = System.currentTimeMillis

	def getDelta = {
		val newTime = System.currentTimeMillis
		val deltaMillis = (newTime - oldTime).toInt
		oldTime = newTime
		// Prevent division by zero errors
		if (deltaMillis < 1) DeltaTime(1) else DeltaTime(deltaMillis)
	}
}
