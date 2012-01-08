package org.frankversnel.poortjes;

import scala.collection.mutable.ListBuffer
import akka.actor.Actor

abstract class ComponentManager extends Actor {
    type T

	private val DoneMessage = "done"

	private val components = ListBuffer[T]()

	import ComponentManager._
	def receive = {
		case Register(c) => forComponent(c) (components += _.asInstanceOf[T])
		case Unregister(c) => forComponent(c) (components -= _.asInstanceOf[T])
		case Process => processComponents
				self.channel tryTell DoneMessage
	}

    // Only processes the component if it is of type T
    private def forComponent[T: Manifest](component: Component) (onComponent: Component => Unit) {
        val isRightComponentType = manifest[T] <:< Manifest.singleType(component)
        if(isRightComponentType) {
            onComponent(component)
        }
    }

	protected def processComponents {
		components.foreach(processComponent _)
	}

	protected def processComponent(component: T)

	protected def allComponents = components.readOnly
}
object ComponentManager {
	sealed trait Action
	case class Register(component: Component) extends Action
	case class Unregister(component: Component) extends Action
	case object Process extends Action
}
