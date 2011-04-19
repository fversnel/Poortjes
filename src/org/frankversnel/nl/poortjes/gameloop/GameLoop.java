package org.frankversnel.nl.poortjes.gameloop;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameLoop {
	private final Timer timer;

	private final List<GameTickListener> listeners =
		Collections.synchronizedList(new LinkedList<GameTickListener>());

	public GameLoop(int delayInMilliSeconds) {
		GameTick gameTick = new GameTick(this, delayInMilliSeconds);
		timer = new Timer(delayInMilliSeconds, new FireGameTick(gameTick));
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void setCoalesce(boolean flag) {
		timer.setCoalesce(flag);
	}

	public void addListener(GameTickListener listener) {
		listeners.add(listener);
	}

	public void removeListener(GameTickListener listener) {
		listeners.remove(listener);
	}

	private class FireGameTick implements ActionListener {
		private GameTick gameTick;

		public FireGameTick(GameTick gameTick) {
			this.gameTick = gameTick;
		}

		public void actionPerformed(ActionEvent e) {
			for(GameTickListener listener :
					new LinkedList<GameTickListener>(listeners)) {
				listener.gameTickOccurred(gameTick);
			}
		}

	}

}
