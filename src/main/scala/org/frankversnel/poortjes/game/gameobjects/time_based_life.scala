package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.Update

/**
 * When this component is attached to a game object that game object will surely die in
 * the specified maxTimeAliveMillis length.
 */
trait TimeBasedLife extends Component {
	protected val maxTimeAliveMillis: Long
	private var timeAliveMillis = 0L

	override def process(update: Update) {
		super.process(update)

		timeAliveMillis += update.deltaTime.millis
		if(timeAliveMillis >= maxTimeAliveMillis) {
			this.destroy
		}
	}

	/**
	 * Return a float value between 0 and 1 where birth is 0 and death is 1.
	 */
	protected def timeToLive: Float = timeAliveMillis.toFloat / maxTimeAliveMillis.toFloat

}
