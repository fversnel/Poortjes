package org.frankversnel.poortjes;

// This should actually be a trait
// So concurrent behaviour can be slapped on to the component manager
// syntax should look like this:
// class XComponentManager extends ComponentManager with ConcurrentComponentProcessing
abstract class ConcurrentComponentManager extends ComponentManager {
	private sealed trait Action
	private case class Register(component: Component) extends Action
	private case class Unregister(component: Component) extends Action
	private case object Process extends Action

	import akka.actor.Actor
	import akka.actor.Actor._
	import akka.actor.ActorRef
	private val componentActor = actorOf(new Actor {
		def receive = {
			case Register(c) => ConcurrentComponentManager.super.addComponent(c)
			case Unregister(c) => ConcurrentComponentManager.super.removeComponent(c)
			case Process => ConcurrentComponentManager.super.processComponents
		}
	}).start

	override def addComponent(component: Component) {
		componentActor ! Register(component)
	}
	override def removeComponent(component: Component) {
		componentActor ! Unregister(component)
	}
	override def processComponents {
		componentActor ! Process
	}

}
