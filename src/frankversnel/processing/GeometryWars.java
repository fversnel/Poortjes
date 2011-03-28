package frankversnel.processing;

import java.util.List;

import processing.core.*;

import frankversnel.processing.gameobjects.Enemy;
import frankversnel.processing.gameobjects.GameObject;
import frankversnel.processing.gameobjects.Player;
import frankversnel.processing.gameobjects.Position;
import frankversnel.processing.gameobjects.wall.BottomWall;
import frankversnel.processing.gameobjects.wall.LeftWall;
import frankversnel.processing.gameobjects.wall.RightWall;
import frankversnel.processing.gameobjects.wall.TopWall;
import frankversnel.processing.util.Color;

public class GeometryWars extends PApplet {
	/**
	 *
	 */
	private static final long serialVersionUID = 9178782595328986939L;

	private static final int SCREEN_WIDTH = 400;
	private static final int SCREEN_HEIGHT = 400;
	private static final int BACKGROUND_COLOR = 0;
	private static final int FOREGROUND_COLOR = 255;

	private GameContext gameContext;

    public void setup() {
	    size(SCREEN_WIDTH, SCREEN_HEIGHT);
	    background(BACKGROUND_COLOR);
	    
	    gameContext = new GameContext();
	    
	    gameContext.addPlayer(new Player(
	    		new Position(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2),
	    		'w', 's', 'a', 'd', Color.GREEN));
	    gameContext.addEnemy(new Enemy(new Position(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4),
				1, Color.RED));

		// Create walls.
	    gameContext.addWall(new LeftWall(SCREEN_WIDTH, SCREEN_HEIGHT));
	    gameContext.addWall(new RightWall(SCREEN_WIDTH, SCREEN_HEIGHT));
	    gameContext.addWall(new TopWall(SCREEN_WIDTH, SCREEN_HEIGHT));
	    gameContext.addWall(new BottomWall(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    public void draw() {
    	List<GameObject> gameObjects = gameContext.getGameObjects();
    	
       	this.clearScreen();
		for(GameObject gameObject : gameObjects) {
			gameObject.render(this.g);
			this.g.fill(FOREGROUND_COLOR);
		}

		// Perform collision detection.
		Visitor collisionVisitor = new CollisionVisitor(gameContext);
    	for(GameObject gameObject : gameObjects) {
    		gameObject.accept(collisionVisitor);
    	}
    }

    public void keyPressed() {
    	for(Player player : gameContext.getPlayers()) {
			player.handleInput(key);
    	}
    }

    private void clearScreen() {
    	background(BACKGROUND_COLOR);
    }

    public static void main(String args[]) {
    	PApplet.main(new String[] { "--present", "frankversnel.processing.GeometryWars" });
    }

}
