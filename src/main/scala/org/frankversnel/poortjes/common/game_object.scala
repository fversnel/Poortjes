package org.frankversnel.poortjes

abstract class GameObject {
	// By default a game object has no parent
	private var _parent: Option[GameObject] = None
	private var _children: List[GameObject] = Nil

	var isDestroyed = false
	def destroy = isDestroyed = true

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

	def parent = _parent
	protected def setParent(newParent: GameObject) {
		_parent = Option(newParent)
		newParent.addChild(this)
	}

	def children = _children
	private def addChild(child: GameObject) {
		_children = child :: _children
	}
}
