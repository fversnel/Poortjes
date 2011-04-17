package org.frankversnel.nl.poortjes.collision;

import java.util.EventListener;

public interface CollisionListener extends EventListener {
	
	public void collision(Collision collision);

}
