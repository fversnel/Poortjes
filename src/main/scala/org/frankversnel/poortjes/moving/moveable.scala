package org.frankversnel.poortjes.moving

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Moveable extends Component with Logging {
	var transform: Transform
	var speed: Speed

	def move {
		//logger.info("Speeding at: " + speed.getDistance + ", " + speed.getRotation)

		transform.rotate(speed.getRotation);
		transform.translate(0, -speed.getDistance);
	}
}
