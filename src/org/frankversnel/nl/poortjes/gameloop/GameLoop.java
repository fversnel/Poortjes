package org.frankversnel.nl.poortjes.gameloop;

import javax.swing.Timer;

public abstract class GameLoop extends Timer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -178112459234430448L;
	
	public GameLoop(int delayInMilliSeconds) {
		super(delayInMilliSeconds, null);
	}

}
