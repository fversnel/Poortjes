package org.frankversnel.nl.poortjes.gameloop;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameLoop {
	private static final long serialVersionUID = -178112459234430448L;

	private static final int INITIAL_DELAY_IN_MILLISECONDS = 0;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private int delayInMilliSeconds;

	private Timer timer;
	private boolean isTimerRunning = false;

	private List<GameTickListener> listeners =
		Collections.synchronizedList(new LinkedList<GameTickListener>());

	public GameLoop(int delayInMilliSeconds) {
		this.delayInMilliSeconds = delayInMilliSeconds;
	}

	public void start() {
		if(!isTimerRunning) {
			timer = new Timer();

			TimerTask fireGameTick = new FireGameTick(new GameTick(this, delayInMilliSeconds));
			timer.scheduleAtFixedRate(fireGameTick, INITIAL_DELAY_IN_MILLISECONDS,
					delayInMilliSeconds);

			isTimerRunning = true;
		} else {
			logger.info("Game loop is already running");
		}
	}

	public void stop() {
		if(isTimerRunning) {
			timer.cancel();

			isTimerRunning = false;
		} else {
			logger.info("Game loop cannot be stopped because it was not started.");
		}
	}

	public void addListener(GameTickListener listener) {
		listeners.add(listener);
	}

	public void removeListener(GameTickListener listener) {
		listeners.remove(listener);
	}

	private class FireGameTick extends TimerTask {
		private GameTick gameTick;

		public FireGameTick(GameTick gameTick) {
			this.gameTick = gameTick;
		}

		public void run() {
			for(GameTickListener listener :
					new LinkedList<GameTickListener>(listeners)) {
				listener.gameTickOccurred(gameTick);
					}
		}
	}

}
