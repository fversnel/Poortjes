package org.frankversnel.nl.poortjes.gameloop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;

public class GameLoop implements ActionListener {
	private static final long serialVersionUID = -178112459234430448L;
	
	private int delayInMilliSeconds;
	
	private Timer timer;
	
    private EventListenerList listenerList = new EventListenerList();

	public GameLoop(int delayInMilliSeconds) {
		this.delayInMilliSeconds = delayInMilliSeconds;
		
		timer = new Timer(delayInMilliSeconds, this);
	}

	public void start() {
		timer.start();
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		fireGameTick(new GameTick(this, delayInMilliSeconds));
	}

    public void addListener(GameTickListener listener) {
        listenerList.add(GameTickListener.class, listener);
    }
    
    public void removeListener(GameTickListener listener) {
        listenerList.remove(GameTickListener.class, listener);
    }
    
    private void fireGameTick(GameTick gameTick) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==GameTickListener.class) {
                ((GameTickListener)listeners[i+1]).gameTickOccurred(gameTick);
            }
        }
    }

}
