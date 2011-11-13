package org.frankversnel.poortjes;

import scala.collection.mutable.ListBuffer
import akka.actor.Actor

abstract class ComponentManager[T] extends Actor {
    private val components = ListBuffer[T]()

    import ComponentManager._
    def receive = {
        case Register(c) => components += c.asInstanceOf[T]
        case Unregister(c) => components -= c.asInstanceOf[T]
        case Process => processComponents
    }

    protected def processComponents: Unit = {
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
