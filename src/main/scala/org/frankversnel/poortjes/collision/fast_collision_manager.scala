package org.frankversnel.poortjes.collision

import scala.collection.mutable.ListBuffer

import org.frankversnel.poortjes._

abstract class FastCollisionManager extends ComponentManager {
	type T = Collidable
	type A <: Collidable
	type B <: Collidable

	private val lhsCollidables = ListBuffer[A]()
	private val rhsCollidables = ListBuffer[B]()

	override def addComponent(component: Component) {
		if(isCorrectLhs(component)) {
			lhsCollidables += component.asInstanceOf[A]
		} else if(isCorrectRhs(component)) {
			rhsCollidables += component.asInstanceOf[B]
		}
	}
	override def removeComponent(component: Component) {
		if(isCorrectLhs(component)) {
			lhsCollidables -= component.asInstanceOf[A]
		} else if(isCorrectRhs(component)) {
			rhsCollidables -= component.asInstanceOf[B]
		}
	}
	override def processComponents(update: Update) {
		for(lhsCollidable <- lhsCollidables;
			rhsCollidable <- rhsCollidables
			if lhsCollidable.collidesWith(rhsCollidable)
		) yield lhsCollidable.onCollision(rhsCollidable)
	}

	protected def isCorrectLhs(component: Component): Boolean
	protected def isCorrectRhs(component: Component): Boolean
	protected def isCorrectType(component: Component) = component.isInstanceOf[Collidable]
}
