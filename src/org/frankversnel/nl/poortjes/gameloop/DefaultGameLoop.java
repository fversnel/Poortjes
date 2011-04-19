package org.frankversnel.nl.poortjes.gameloop;

public class DefaultGameLoop extends GameLoop {
	private final static int DELAY_IN_MILLISECONDS = 1000 / 60;

	public DefaultGameLoop() {
		super(DELAY_IN_MILLISECONDS);

		// We don't want to miss any events.
		setCoalesce(false);
	}

}
