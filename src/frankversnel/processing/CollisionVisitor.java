package frankversnel.processing;

import frankversnel.processing.gameobjects.Enemy;
import frankversnel.processing.gameobjects.GameObject;
import frankversnel.processing.gameobjects.Player;
import frankversnel.processing.gameobjects.gate.Gate;
import frankversnel.processing.gameobjects.gate.Knob;
import frankversnel.processing.gameobjects.gate.Stick;
import frankversnel.processing.gameobjects.wall.Wall;

/**
 * @author Frank Versnel
 */
public class CollisionVisitor implements Visitor {

	
	private final GameContext gameContext;
	
	public CollisionVisitor(GameContext gameContext) {
		this.gameContext = gameContext;
	}
	
	@Override
	public void visit(Wall wall) {
		for(GameObject gameObject : gameContext.getGameObjects()) {
			if(gameObject.collides(wall)) {
				// TODO Don't move through walls
			}
		}
	}

    @Override
    public void visit(Player player) {
    }

    @Override
    public void visit(Enemy enemy) {
    	for(Player player : gameContext.getPlayers()) {
    		if(player.collides(enemy)) {
    			// TODO Kill player
    		}
    	}
    }

	@Override
	public void visit(Knob knob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Gate gate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Stick stick) {
		// TODO Auto-generated method stub
		
	}

}
