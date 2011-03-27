package frankversnel.processing;

import java.util.LinkedList;
import java.util.List;

import processing.core.*;

public class GeometryWars extends PApplet {
	/**
	 *
	 */
	private static final long serialVersionUID = 9178782595328986939L;

	private static final int SCREEN_WIDTH = 400;
	private static final int SCREEN_HEIGHT = 400;
	private static final int BACKGROUND_COLOR = 0;
	private static final int FOREGROUND_COLOR = 255;

	private List<GameObject> gameObjects;
	private List<Player> players;
	private List<Enemy> enemies;

    public void setup() {
	    size(SCREEN_WIDTH, SCREEN_HEIGHT);
	    background(BACKGROUND_COLOR);

	    this.gameObjects = new LinkedList<GameObject>();
		this.players = new LinkedList<Player>();
		this.enemies = new LinkedList<Enemy>();

		// Create players.
		this.players.add(new Player(
	    		new Position(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2),
	    		'w', 's', 'a', 'd', Color.GREEN));
		// TODO Create enemies.
		this.enemies.add(new Enemy(new Position(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4),
					1, Color.RED));

		// Put them into one pool.
		this.gameObjects.addAll(this.players);
		this.gameObjects.addAll(this.enemies);

		// TODO Create walls
		this.gameObjects.add(new LeftWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.gameObjects.add(new RightWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.gameObjects.add(new TopWall(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.gameObjects.add(new BottomWall(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    public void draw() {
    	this.clearScreen();
		for(GameObject gameObject : gameObjects) {
			gameObject.render(this.g);
			this.g.fill(FOREGROUND_COLOR);
		}

		// Perform collision detection.
		for(GameObject currentObject : this.gameObjects) {
			List<GameObject> otherObjects = new LinkedList<GameObject>(this.gameObjects);
			otherObjects.remove(currentObject);

			for(GameObject otherObject : otherObjects) {
				if(currentObject.collides(otherObject)) {
					System.out.println("collision");
				}
			}
		}
    }

    public void keyPressed() {
    	for(Player player : this.players) {
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
