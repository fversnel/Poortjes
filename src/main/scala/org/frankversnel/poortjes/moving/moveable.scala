package org.frankversnel.poortjes.moving

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes._

trait Moveable extends Component with Logging {
	self: Speed with Transform =>

	def move {
		//logger.info("Speeding at: " + self.distanceSpeed + ", " + self.rotationSpeed)

		self.rotate(self.rotationSpeed);
		self.translate(0, -self.distanceSpeed);
	}
}
