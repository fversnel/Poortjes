import scala.collection.mutable.ListBuffer

abstract class Observer {
    def receive(): Unit
}

abstract class Events[+A] {
    def subscribe(ob: Observer): Unit
    def message(ob: Observer): Option[A]
}
abstract class EventSource[A] extends Events[A] {
    def emit(ev: A): Unit
    // ...
}

class Button(label: String) {
    val clicks: Events[Int] = new EventSource[Int] {
        // call 'this raise x' for each system event
        def emit(ev: Int) = {}
        def subscribe(ob: Observer): Unit = {}
        def message(ob: Observer): Option[Int] = { Option(2) }
    }
}

trait Observing {
    private val obRefs = new ListBuffer[Observer]()

    abstract class PersistentObserver extends Observer {
        obRefs += this
    }

    protected def observe[A](event: Events[A])(op: Option[A] => Unit) = {
        event.subscribe(new PersistentObserver {
            def receive() { op(event.message(this)) }
        })
    }
}

object EventsExample extends App with Observing {
    // ...
    val quitButton = new Button("Quit")
    observe(quitButton.clicks) { x => println("Received event: " + x) }
}
