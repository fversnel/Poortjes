package frankversnel.processing;

import frankversnel.processing.gameobjects.Enemy;
import frankversnel.processing.gameobjects.Player;
import frankversnel.processing.gameobjects.gate.Gate;
import frankversnel.processing.gameobjects.gate.Knob;
import frankversnel.processing.gameobjects.gate.Stick;
import frankversnel.processing.gameobjects.wall.Wall;

/**
 * @author Frank Versnel
 */
public interface Visitor {
	
    public void visit(Knob knob);
    
    public void visit(Enemy enemy);

	public void visit(Wall wall);

	public void visit(Gate gate);

	public void visit(Stick stick);

	public void visit(Player player);
    
}
