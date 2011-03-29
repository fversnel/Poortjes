package frankversnel.processing;

import java.util.List;

import frankversnel.processing.component.manager.Processing2DRenderer;
import frankversnel.processing.component.manager.RenderManager;
import frankversnel.processing.gameobject.Player;

import processing.core.*;

public class Poortjes extends PApplet {
	/**
	 *
	 */
	private static final long serialVersionUID = 9178782595328986939L;

	private static final int SCREEN_WIDTH = 400;
	private static final int SCREEN_HEIGHT = 400;
	private static final int BACKGROUND_COLOR = 0;
	private static final int FOREGROUND_COLOR = 255;
	
	private RenderManager renderManager;
	
    public void setup() {
	    size(SCREEN_WIDTH, SCREEN_HEIGHT);
	    background(BACKGROUND_COLOR);
	    
	    renderManager = new RenderManager(new Processing2DRenderer(g));
	    
	    new Player(renderManager);
    }

    public void draw() {
    	clearScreen();
    	renderManager.drawAll();
    }

    private void clearScreen() {
    	background(BACKGROUND_COLOR);
    }

    public static void main(String args[]) {
    	PApplet.main(new String[] { "--present", "frankversnel.processing.Poortjes" });
    }

}