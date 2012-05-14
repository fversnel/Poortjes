package org.frankversnel.poortjes

import scala.collection.mutable.ListBuffer

import org.frankversnel.poortjes.util.DeltaTime

abstract class ComponentManager {
	type T

	private var components = ListBuffer[T]()

	def addComponent(component: Component) {
		forComponent(component) (components += _.asInstanceOf[T])
	}
	def removeComponent(component: Component) {
		forComponent(component) (components -= _.asInstanceOf[T])
	}

	protected def allComponents = components.toList

	def processComponents(update: Update): Unit
	protected def isCorrectType(component: Component): Boolean

	// Only processes the component if it is of type T
	private def forComponent[T: Manifest](c: Component) (onComponent: Component => Unit) {
		if(isCorrectType(c)) {
			onComponent(c)
		}
	}
}
