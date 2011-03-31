package frankversnel.processing.movement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Angle;
import frankversnel.processing.component.Component;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Speed;

public class Moveable extends Component implements ActionListener {

	public Moveable(GameObject gameObject) {
		super(gameObject);
	}
	
	public void move() {
		Position position = getGameObject().safe_getComponent(Position.class);
		float speed = getGameObject().safe_getComponent(Speed.class).getValue();
		float angle = getGameObject().safe_getComponent(Angle.class).getRadians();
		
		final float newXCoordinate = position.x() + speed
				* (float)Math.sin(angle);
		final float newYCoordinate = position.y() - speed
				* (float)Math.cos(angle);
		
		position.x(newXCoordinate);
		position.y(newYCoordinate);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		move();
	}

}
