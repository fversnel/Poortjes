package org.frankversnel.nl.poortjes.gameloop;

public class DefaultGameLoop extends GameLoop {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7172809927442261650L;

	private static final int DELAY_IN_MILLISECONDS = 1000 / 60;

	// private static final int DELAY_IN_MILLISECONDS = 2000;

	public DefaultGameLoop() {
		super(DELAY_IN_MILLISECONDS);
	}

}
