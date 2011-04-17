package org.frankversnel.nl.poortjes.gameloop;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class GameLoop {
	private static final long serialVersionUID = -178112459234430448L;

	private static final int INITIAL_DELAY_IN_MILLISECONDS = 0;

	private int delayInMilliSeconds;

	private Timer timer;
	private TimerTask fireGameTick;

	private List<GameTickListener> listeners =
		Collections.synchronizedList(new LinkedList<GameTickListener>());

	public GameLoop(int delayInMilliSeconds) {
		this.delayInMilliSeconds = delayInMilliSeconds;

		timer = new Timer();
	}

	public void start() {
		fireGameTick = new FireGameTick(new GameTick(this, delayInMilliSeconds));
		timer.scheduleAtFixedRate(fireGameTick, INITIAL_DELAY_IN_MILLISECONDS,
				delayInMilliSeconds);
	}

	public void stop() {
		if(fireGameTick == null) {
			throw new GameLoopNotStartedException(
					"Game loop cannot be stopped because it was never started.");
		}

		fireGameTick.cancel();
		timer.purge();
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
