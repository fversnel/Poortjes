package org.frankversnel.nl.poortjes.animation;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameTick;
import org.frankversnel.nl.poortjes.gameloop.GameTickListener;

public abstract class Animation extends Component implements GameTickListener {
	private GameLoop gameLoop;

	public Animation(GameObject gameObject, GameLoop gameLoop) {
		super(gameObject);

		this.gameLoop = gameLoop;
		gameLoop.addListener(this);
	}

	public abstract void animate(int timeSinceLastEvent);
	
	@Override
	public void remove() {
		gameLoop.removeListener(this);
	}

	@Override
	public void gameTickOccurred(GameTick gameTick) {
		animate(gameTick.getDelayInMilliSeconds());
	}

}
