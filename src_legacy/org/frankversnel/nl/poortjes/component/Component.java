package org.frankversnel.nl.poortjes.component;

import org.frankversnel.nl.poortjes.GameObject;

public abstract class Component {
	private GameObject gameObject;

	public Component(GameObject gameObject) {
		this.gameObject = gameObject;

		gameObject.addComponent(this);
	}

	public GameObject getGameObject() {
		return this.gameObject;
	}

	public void remove() {
		// Necessary garbage collection.
		gameObject = null;
	}

}
