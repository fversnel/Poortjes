package org.frankversnel.poortjes

abstract class GameObject {
	// By default a game object has no parent
	val parent: Option[GameObject] = None

	def as[A: Manifest] : Option[A] = {
		if (Manifest.singleType(this) <:< manifest[A]) {
			Some(this.asInstanceOf[A])
		} else {
			None
		}
	}
}
