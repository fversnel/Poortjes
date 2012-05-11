package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes._

/**
 * Keeps track of a game object's life time
 */
trait LifeTime extends Component {

	private var _timeAliveMillis = 0L

	override def process(update: Update) {
		_timeAliveMillis += update.deltaTime.millis
	}

	def timeAliveMillis: Long = _timeAliveMillis
	def timeAliveSeconds: Float = _timeAliveMillis.toFloat / 1000f
}
