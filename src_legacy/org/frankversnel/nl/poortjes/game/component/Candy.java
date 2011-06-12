package org.frankversnel.nl.poortjes.game.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;

public class Candy extends Component {

	public Candy(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void remove() {
		// No need to remove anything.
	}

}
