package org.frankversnel.poortjes

abstract class GameObject {
	// By default a game object has no parent
	private var _parent: Option[GameObject] = None
	private var _children: List[GameObject] = Nil

	private var _isDestroyed = false
	def destroy = _isDestroyed = true
	def isDestroyed = _isDestroyed

	def parent = _parent
	def parent_= (value: GameObject): Unit = {
		_parent = Option(value)
		_parent.get._children = this :: parent.get._children
	}

	def children = _children

	def onGameObjectAndChildren(apply: GameObject => Unit) {
		apply(this)
		children.foreach(_.onGameObjectAndChildren(apply))
	}
}
