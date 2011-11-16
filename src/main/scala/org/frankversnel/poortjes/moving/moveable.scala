package org.frankversnel.poortjes.moving

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Moveable extends Component with Logging {
	self: Speed with Transform =>

	def move {
		//logger.info("Speeding at: " + speed.getDistance + ", " + speed.getRotation)

		self.rotate(self.getRotation);
		self.translate(0, -self.getDistance);
	}
}
