package org.frankversnel.poortjes.game.gameobjects

trait SummoningSickness extends LifeTime {
	protected val maxSicknessDurationMillis: Long

	def hasSummoningSickness = timeAliveMillis < maxSicknessDurationMillis
}
