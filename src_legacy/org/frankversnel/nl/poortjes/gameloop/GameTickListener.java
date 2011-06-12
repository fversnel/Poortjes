package org.frankversnel.nl.poortjes.gameloop;

import java.util.EventListener;

public interface GameTickListener extends EventListener {
	
	public void gameTickOccurred(GameTick gameTick);
	
}