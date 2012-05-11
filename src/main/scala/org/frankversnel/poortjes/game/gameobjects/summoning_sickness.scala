package org.frankversnel.poortjes.game.gameobjects

import org.frankversnel.poortjes._

trait SummoningSickness extends Component {
	protected val maxSicknessDurationMillis: Long

	private var sicknessDurationMillis = 0L

	override def process(update: Update) {
		sicknessDurationMillis += update.deltaTime.millis
	}

	def hasSummoningSickness = sicknessDurationMillis < maxSicknessDurationMillis
}
