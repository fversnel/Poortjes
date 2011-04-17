package org.frankversnel.nl.poortjes.gameloop;

public class DefaultGameLoop extends GameLoop {
	private static final long serialVersionUID = 7172809927442261650L;

	private final static int DELAY_IN_MILLISECONDS = 10;

	public DefaultGameLoop() {
		super(DELAY_IN_MILLISECONDS);
	}

}
