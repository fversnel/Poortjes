package org.frankversnel.poortjes.game.gameobjects

trait SummoningSickness extends LifeTimeCounter {
	protected val maxSicknessDurationMillis: Long

	def hasSummoningSickness = timeAliveMillis < maxSicknessDurationMillis
}
