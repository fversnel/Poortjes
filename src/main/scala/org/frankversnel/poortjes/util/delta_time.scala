package org.frankversnel.poortjes.util

case class DeltaTime(millis: Int) {
	val seconds: Float = millis.toFloat / 1000f
}

