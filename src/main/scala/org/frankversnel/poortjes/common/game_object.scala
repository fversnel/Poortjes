package org.frankversnel.poortjes

abstract class GameObject {

	def as[A: Manifest] : Option[A] = {
		if (Manifest.singleType(this) <:< manifest[A]) {
			Some(this.asInstanceOf[A])
		} else {
			None
		}
	}
}
