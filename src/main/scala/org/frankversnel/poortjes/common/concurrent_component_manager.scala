package org.frankversnel.poortjes;

trait ConcurrentProcessing extends ComponentManager {
	private sealed trait Action
	private case class Register(component: Component) extends Action
	private case class Unregister(component: Component) extends Action
	private case object Process extends Action

	import akka.actor.Actor
	import akka.actor.Actor._
	import akka.actor.ActorRef
	private val componentActor = actorOf(new Actor {
		def receive = {
			case Register(c) => ConcurrentProcessing.super.addComponent(c)
			case Unregister(c) => ConcurrentProcessing.super.removeComponent(c)
			case Process => ConcurrentProcessing.super.processComponents
		}
	}).start

	abstract override def addComponent(component: Component) {
		componentActor ! Register(component)
	}
	abstract override def removeComponent(component: Component) {
		componentActor ! Unregister(component)
	}
	abstract override def processComponents {
		componentActor ! Process
	}

}
