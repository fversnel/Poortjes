package org.frankversnel.poortjes.moving

import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.Update
import org.frankversnel.poortjes.Component

trait Moveable extends Component {
	self: Speed with Transform =>

	override def process(update: Update) {
		super.process(update)

		self.rotate(self.rotationSpeed * update.deltaTime.millis);
		self.translate(self.moveSpeed.getX * update.deltaTime.millis, -self.moveSpeed.getY * update.deltaTime.millis);
	}
}
