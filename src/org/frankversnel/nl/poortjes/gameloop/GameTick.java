package org.frankversnel.nl.poortjes.gameloop;

import java.util.EventObject;

public class GameTick extends EventObject {
	private static final long serialVersionUID = -9132071990983642027L;
	
	private int delayInMilliSeconds;

	public GameTick(Object source, int delayInMilliSeconds) {
		super(source);
		
		this.delayInMilliSeconds = delayInMilliSeconds;
	}
	
	public int getDelayInMilliSeconds() {
		return delayInMilliSeconds;
	}

}
