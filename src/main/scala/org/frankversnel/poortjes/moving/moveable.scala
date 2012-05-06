package org.frankversnel.poortjes.moving

import org.frankversnel.poortjes.Transform
import org.frankversnel.poortjes.Component
import org.frankversnel.poortjes.util.DeltaTime

trait Moveable extends Component {
	self: Speed with Transform =>

	override def process(deltaTime: DeltaTime) {
		super.process(deltaTime)

		self.rotate(self.rotationSpeed * deltaTime.millis);
		self.translate(self.moveSpeed.getX * deltaTime.millis, -self.moveSpeed.getY * deltaTime.millis);
	}
}
