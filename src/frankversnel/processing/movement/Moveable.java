package frankversnel.processing.movement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Component;
import frankversnel.processing.component.Speed;
import frankversnel.processing.component.Transform;
import frankversnel.processing.gameloop.GameLoop;

public class Moveable extends Component implements ActionListener {

	public Moveable(GameObject gameObject, GameLoop gameLoop) {
		super(gameObject);
		
		gameLoop.addActionListener(this);
	}
	
	public void move(int timeSinceLastEvent) {
		Transform transform = getGameObject().safe_getComponent(Transform.class);
		Speed speed = getGameObject().safe_getComponent(Speed.class);
		
		transform.rotate(speed.getRotation() * timeSinceLastEvent);
		transform.translate(speed.getDistance() * timeSinceLastEvent
				, speed.getDistance() * timeSinceLastEvent);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int timeSinceLastEvent = ((GameLoop)event.getSource()).getDelay();
		move(timeSinceLastEvent);
	}

}
