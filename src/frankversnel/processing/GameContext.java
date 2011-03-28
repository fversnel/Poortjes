package frankversnel.processing;

import java.util.LinkedList;
import java.util.List;

import frankversnel.processing.gameobjects.Enemy;
import frankversnel.processing.gameobjects.GameObject;
import frankversnel.processing.gameobjects.Player;
import frankversnel.processing.gameobjects.wall.Wall;

public class GameContext {
	private final List<GameObject> gameObjects = new LinkedList<GameObject>();
	
	private final List<Player> players = new LinkedList<Player>();
	private final List<Enemy> enemies = new LinkedList<Enemy>();
	private final List<Wall> walls = new LinkedList<Wall>();
	
	public void addPlayer(Player player) {
		this.players.add(player);
		gameObjects.add(player);
		
	}
	public void addEnemy(Enemy enemy) {
		this.enemies.add(enemy);
		gameObjects.add(enemy);
	}
	
	public void addWall(Wall wall) {
		this.walls.add(wall);
		gameObjects.add(wall);
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}
	public List<Wall> getWalls() {
		return walls;
	}
	
	public List<Player> getPlayers() {
		return this.players;
	}
	
	public List<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	
	public void removeGameObject(final GameObject objectToRemove) {
		this.gameObjects.remove(objectToRemove);
		
		this.removeFromContext(this.players, objectToRemove);
		this.removeFromContext(this.enemies, objectToRemove);
	}
	
	private void removeFromContext(final List<? extends GameObject> toRemoveFrom, final GameObject objectToRemove) {
		for(GameObject object : new LinkedList<GameObject>(toRemoveFrom)) {
			if(!this.gameObjects.contains(object)) {
				toRemoveFrom.remove(object);
			}
		}
	}

}
