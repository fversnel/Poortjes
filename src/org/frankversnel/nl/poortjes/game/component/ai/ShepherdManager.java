package org.frankversnel.nl.poortjes.game.component.ai;

import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.game.component.Player;

public class ShepherdManager extends ComponentManager<Shepherd> {
	List<Player> players = new LinkedList<Player>(); 

	@Override
	public void processComponents() {
	}

}
