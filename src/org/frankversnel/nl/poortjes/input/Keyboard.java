package org.frankversnel.nl.poortjes.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Keyboard implements KeyListener {
	private static Logger logger = LoggerFactory.getLogger(Keyboard.class);

	@Override
	public void keyPressed(KeyEvent arg0) {
		char keyPressed = arg0.getKeyChar();
		
		// DEBUG CODE
		if(keyPressed == 's') {
			logger.info("Key " + keyPressed);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
