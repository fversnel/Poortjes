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
	def processComponents(deltaTime: DeltaTime) {
		components.toList.foreach(processComponent(_, deltaTime))
	}

	protected def isCorrectType(component: Component): Boolean
	protected def processComponent(component: T, deltaTime: DeltaTime): Unit

	protected def allComponents = components.toList

	// Only processes the component if it is of type T
	private def forComponent[T: Manifest](c: Component) (onComponent: Component => Unit) {
		if(isCorrectType(c)) {
			onComponent(c)
		}
	}
}
