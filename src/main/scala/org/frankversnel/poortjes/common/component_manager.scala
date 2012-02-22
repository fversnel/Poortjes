package org.frankversnel.poortjes

import scala.collection.mutable.ListBuffer

abstract class ComponentManager {
	type T

	private var components = ListBuffer[T]()

	def addComponent(component: Component) {
		forComponent(component) (components += _.asInstanceOf[T])
	}
	def removeComponent(component: Component) {
		forComponent(component) (components -= _.asInstanceOf[T])
	}
	def processComponents {
		components.foreach(processComponent _)
	}

	protected def isCorrectType(component: Component): Boolean
	protected def processComponent(component: T): Unit

	protected def allComponents = components.readOnly

    // Only processes the component if it is of type T
    private def forComponent[T: Manifest](component: Component) (onComponent: Component => Unit) {
        if(isCorrectType(component)) {
            onComponent(component)
        }
    }
}
