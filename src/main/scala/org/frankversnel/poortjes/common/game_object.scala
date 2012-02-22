package org.frankversnel.poortjes

abstract class GameObject {
	// By default a game object has no parent
	val parent: Option[GameObject] = None

	var isDestroyed = false

	def as[A: Manifest]: Option[A] = {
		if (is[A]) {
			Some(this.asInstanceOf[A])
		} else {
			None
		}
	}

	def is[A: Manifest]: Boolean = {
		Manifest.singleType(this) <:< manifest[A]
    }
}
