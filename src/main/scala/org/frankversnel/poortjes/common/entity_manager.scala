package org.frankversnel.poortjes

import org.slf4j.scala.Logging;

import org.frankversnel.poortjes.rendering._;
import org.frankversnel.poortjes.collision._
import org.frankversnel.poortjes.util.DeltaTime

import org.frankversnel.poortjes.game.gameobjects.Player

class EntityManager private(private val componentManagers: List[ComponentManager]) extends Logging {

	private var _gameObjects: List[GameObject] = Nil
	def gameObjects = _gameObjects

    def spawn(newGameObject: GameObject) {
		_gameObjects ::= newGameObject

		newGameObject.onGameObjectAndChildren { gameObject =>
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
			destroyedGameObject.onGameObjectAndChildren { gameObject =>
				componentManagers.foreach(_.removeComponent(gameObject.asInstanceOf[Component]))
			}
		}
	}
}
object EntityManager {
	var entityManager: Option[EntityManager] = None
	def initialize(componentManagers: List[ComponentManager]) {
		entityManager = Some(new EntityManager(componentManagers))
	}

	def apply() = {
		require(entityManager.isDefined, "Entity manager must be initialized first")
		entityManager.get
	}
}
