package frankversnel.processing;

import java.util.LinkedList;
import java.util.List;

import processing.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeometryWars extends PApplet {
	/**
	 *
	 */
	private static final long serialVersionUID = 9178782595328986939L;

	private Logger logger = LoggerFactory.getLogger(GeometryWars.class);

	private static final int SCREEN_WIDTH = 400;
	private static final int SCREEN_HEIGHT = 400;
	private static final int BACKGROUND_COLOR = 0;
	private static final int FOREGROUND_COLOR = 255;

	private List<GameObject> gameObjects = new LinkedList<GameObject>();
	
	private List<Player> players = new LinkedList<Player>();
	private List<Enemy> enemies = new LinkedList<Enemy>();
	private List<Wall> walls = new LinkedList<Wall>();

    public void setup() {
	    size(SCREEN_WIDTH, SCREEN_HEIGHT);
	    background(BACKGROUND_COLOR);
	    
		// Create players.
		this.players.add(new Player(
	    		new Position(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2),
	    		'w', 's', 'a', 'd', Color.GREEN));
		
		// Create enemies.
		this.enemies.add(new Enemy(new Position(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4),
					1, Color.RED));

		// Create walls.
		this.walls.add(new LeftWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.walls.add(new RightWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.walls.add(new TopWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.walls.add(new BottomWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		
		saveGameObjects(this.walls, this.enemies, this.players);
    }

    public void draw() {
    	this.clearScreen();
		for(GameObject gameObject : gameObjects) {
			gameObject.render(this.g);
			this.g.fill(FOREGROUND_COLOR);
		}

		// Perform collision detection.
		performCollision(players, enemies);
		performCollision(players, walls);
		performCollision(enemies, walls);
    }

    public void keyPressed() {
    	for(Player player : this.players) {
			player.handleInput(key);
    	}
    }
    
    private void saveGameObjects(List<? extends GameObject> ... gameObjectsToSave) {
    	for(List<? extends GameObject> gameObjectsCollection : gameObjectsToSave) {
    		gameObjects.addAll(gameObjectsCollection);
    	}
    }
    
	private void performCollision(final List<? extends GameObject> us, final List<? extends GameObject> them) {
    	for(GameObject ours : us) {
    		for(GameObject theirs : them) {
    			if(ours.collides(theirs)) {
    				logger.info(ours + " collides with " + theirs);
    			}
    		}
    	}
    }

    private void clearScreen() {
    	background(BACKGROUND_COLOR);
    }

    public static void main(String args[]) {
    	PApplet.main(new String[] { "--present", "frankversnel.processing.GeometryWars" });
    }

}
