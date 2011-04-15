package org.frankversnel.nl.poortjes.test;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;

public class MockComponent extends Component {

	public MockComponent(GameObject gameObject) {
		super(gameObject);
	}

	public void remove() {
		// Nothing to remove
	}

}
