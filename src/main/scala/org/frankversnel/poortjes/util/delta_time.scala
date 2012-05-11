package org.frankversnel.poortjes.util

case class DeltaTime(millis: Long) {
	val seconds: Float = millis.toFloat / 1000f
}

