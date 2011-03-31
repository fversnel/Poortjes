package frankversnel.processing.component;

import frankversnel.processing.GameObject;

public class Speed extends Component {
	private float value;

	public Speed(GameObject gameObject, float value) {
		super(gameObject);
		
		this.value = value;
	}
	
	public float getValue() {
		return this.value;
	}

}
