package frankversnel.processing.component;

import frankversnel.processing.GameObject;

public abstract class Component {
	private GameObject gameObject;

	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
		
		gameObject.addComponent(this);
	}
	
	public GameObject getGameObject() {
		return this.gameObject;
	}
	
}
