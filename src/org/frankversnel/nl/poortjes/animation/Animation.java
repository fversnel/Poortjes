package org.frankversnel.nl.poortjes.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;

public abstract class Animation extends Component implements ActionListener {
	private GameLoop gameLoop;

	public Animation(GameObject gameObject, GameLoop gameLoop) {
		super(gameObject);

		this.gameLoop = gameLoop;
		gameLoop.addActionListener(this);
	}

	public abstract void animate(int timeSinceLastEvent);

	@Override
	public void actionPerformed(ActionEvent event) {
		int timeSinceLastEvent = ((GameLoop) event.getSource()).getDelay();
		animate(timeSinceLastEvent);
	}

	@Override
	public void remove() {
		gameLoop.removeActionListener(this);
	}

}
