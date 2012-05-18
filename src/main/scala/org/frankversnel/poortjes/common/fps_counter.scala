package org.frankversnel.poortjes

class FramerateCounter {
	private val RefreshRate = 500

	private var millisLeftUntilNextFramerate = RefreshRate
	private var currentFramerate = 0

	def framerate(update: Update) = {
		if(millisLeftUntilNextFramerate < 0) {
			currentFramerate = update.framerate
			millisLeftUntilNextFramerate = RefreshRate
		}
		millisLeftUntilNextFramerate -= update.deltaTime.millis

		currentFramerate
	}
}
