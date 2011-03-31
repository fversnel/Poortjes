package frankversnel.processing;


import frankversnel.processing.rendering.Processing2DRenderer;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.shapeloader.ProcessingShapeLoader;

import processing.core.*;

public class Poortjes extends PApplet {
	/**
	 *
	 */
	private static final long serialVersionUID = 9178782595328986939L;

	private static final int SCREEN_WIDTH = 400;
	private static final int SCREEN_HEIGHT = 400;
	private static final int BACKGROUND_COLOR = 0;
	
	private RenderingManager renderManager;
	
    public void setup() {
	    size(SCREEN_WIDTH, SCREEN_HEIGHT);
	    background(BACKGROUND_COLOR);
	    smooth();
	    
	    ProcessingShapeLoader shapeLoader = new ProcessingShapeLoader(this);
	    renderManager = new RenderingManager(new Processing2DRenderer(g, shapeLoader));
	    
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