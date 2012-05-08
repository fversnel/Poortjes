package org.frankversnel.poortjes

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.util.DeltaTime

class EntityManager private(val renderer: Renderer) extends Logging {

	private var _gameObjects: List[GameObject] = Nil
	// Getter
	def gameObjects = _gameObjects

	private val componentManagers = List(
			new CollisionManager,
			new RenderingManager(renderer),
			new ComponentProcessingManager
		)

    def spawn(newGameObject: GameObject) {
		_gameObjects ::= newGameObject

		onGameObjectAndChildren(newGameObject) { gameObject =>
			val component = gameObject.asInstanceOf[Component]
			componentManagers.foreach(_.addComponent(component))
		}
	}

    def process(deltaTime: DeltaTime) {
		componentManagers.foreach {
			_.processComponents(Update(deltaTime, _gameObjects))
		}
    }

	def cleanUp {
		val destroyedGameObjects = gameObjects.filter(_.isDestroyed)

		_gameObjects = _gameObjects.filterNot(g => destroyedGameObjects.contains(g))

		destroyedGameObjects.foreach { destroyedGameObject =>
			onGameObjectAndChildren(destroyedGameObject) { gameObject =>
				componentManagers.foreach(_.removeComponent(gameObject.asInstanceOf[Component]))
			}
		}
	}

	private def onGameObjectAndChildren(gameObject: GameObject)(apply: GameObject => Unit) {
		apply(gameObject)
		gameObject.children.foreach { childGameObject =>
			onGameObjectAndChildren(childGameObject)(apply)
		}
	}
}
object EntityManager {
	var entityManager: Option[EntityManager] = None
	def initialize(renderer: Renderer) {
		entityManager = Some(new EntityManager(renderer))
	}

	def apply() = {
		require(entityManager.isDefined, "Entity manager must be initialized first")
		entityManager.get
	}
}
