package org.frankversnel.nl.poortjes.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Speed;

public class Keyboard extends InputDevice implements KeyListener {
	private char keyForward;
	private char keyBackwards;
	private char keyTurnLeft;
	private char keyTurnRight;
	
	public Keyboard(GameObject gameObject, 
			char keyForward, char keyBackwards, char keyTurnLeft, char keyTurnRight) {
		super(gameObject);
		
		this.keyForward = keyForward;
		this.keyBackwards = keyBackwards;
		this.keyTurnLeft = keyTurnLeft;
		this.keyTurnRight = keyTurnRight;
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		setSpeed(keyEvent.getKeyChar(), 1);
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		setSpeed(keyEvent.getKeyChar(), 0);
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
		// We don't care about what happens when this event is triggered, 
		// we don't need it to move our objects.
	}
	
	private void setSpeed(char keyPressed, int speedValue) {
		Speed speed = getGameObject().safe_getComponent(Speed.class);
		
		if(keyPressed == keyForward) {
			speed.move(speedValue);
		} else if(keyPressed == keyBackwards) {
			speed.move(speedValue * -1);
		} else if(keyPressed == keyTurnLeft) {
			speed.rotate(speedValue * -1);
		} else if(keyPressed == keyTurnRight) {
			speed.rotate(speedValue);
		}
	}

}
