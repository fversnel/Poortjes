package frankversnel.processing.gameloop;

import javax.swing.Timer;

public abstract class GameLoop extends Timer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -178112459234430448L;
	
	private final int delayInMilliSeconds;

	public GameLoop(int delayInMilliSeconds) {
		super(delayInMilliSeconds, null);
		
		this.delayInMilliSeconds = delayInMilliSeconds;
	}
	
	public int getDelayInMilliSeconds() {
		return delayInMilliSeconds;
	}

}
