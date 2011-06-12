package org.frankversnel.nl.poortjes.gameloop;

public class RenderGameLoop extends GameLoop {
	private final static int FRAME_RATE = 60;
	private final static int DELAY_IN_MILLI_SECONDS = 1000 / FRAME_RATE;
	
	public RenderGameLoop() {
		super(DELAY_IN_MILLI_SECONDS);
	}

}
