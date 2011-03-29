package frankversnel.processing.component;

import frankversnel.processing.gameobject.GameObject;

public abstract class Component {
	private GameObject gameObject;

	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public GameObject getGameObject() {
		return this.gameObject;
	}
	
}
