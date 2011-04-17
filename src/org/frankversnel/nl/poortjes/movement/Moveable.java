package org.frankversnel.nl.poortjes.movement;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameTick;
import org.frankversnel.nl.poortjes.gameloop.GameTickListener;

public class Moveable extends Component implements GameTickListener {
	private GameLoop gameLoop;

	public Moveable(GameObject gameObject, GameLoop gameLoop) {
		super(gameObject);

		this.gameLoop = gameLoop;
		gameLoop.addListener(this);
	}

	public void move(int timeSinceLastEvent) {
		Transform transform = getGameObject().getComponent(Transform.class);
		Speed speed = getGameObject().getComponent(Speed.class);

		if (transform != null && speed != null) {
			transform.rotate(speed.getRotation() * timeSinceLastEvent);
			transform.translate(0, -(speed.getDistance() * timeSinceLastEvent));
		}
	}

	@Override
	public void gameTickOccurred(GameTick gameTick) {
		move(gameTick.getDelayInMilliSeconds());
	}

	@Override
	public void remove() {
		gameLoop.removeListener(this);
	}


}
