package org.frankversnel.nl.poortjes.movement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;


public class Moveable extends Component implements ActionListener {

	public Moveable(GameObject gameObject, GameLoop gameLoop) {
		super(gameObject);
		
		gameLoop.addActionListener(this);
	}
	
	public void move(int timeSinceLastEvent) {
		Transform transform = getGameObject().safe_getComponent(Transform.class);
		Speed speed = getGameObject().safe_getComponent(Speed.class);
		
		transform.rotate(speed.getRotation() * timeSinceLastEvent);
		transform.translate(speed.getDistance() * timeSinceLastEvent
				, speed.getDistance() * timeSinceLastEvent);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int timeSinceLastEvent = ((GameLoop)event.getSource()).getDelay();
		move(timeSinceLastEvent);
	}

}
