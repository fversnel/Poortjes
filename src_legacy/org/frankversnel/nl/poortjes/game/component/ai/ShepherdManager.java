package org.frankversnel.nl.poortjes.game.component.ai;

import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.game.component.Player;

public class ShepherdManager extends ComponentManager<Shepherd> {
	List<Player> players = new LinkedList<Player>();

	public ShepherdManager(GameLoop gameLoop) {
		super(gameLoop);
	}

	@Override
	public void processComponents() {
	}

}
