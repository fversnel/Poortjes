package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

class DeltaTimeTracker {
	private var oldTime = System.currentTimeMillis

	def getDelta = {
		val newTime = System.currentTimeMillis
		val deltaMillis = DeltaTime((newTime - oldTime).toInt)
		oldTime = newTime
		deltaMillis
	}
}
