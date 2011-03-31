package frankversnel.processing.collision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import frankversnel.processing.collision.component.Collidable;
import frankversnel.processing.component.ComponentManager;

public class CollisionManager extends ComponentManager<Collidable> implements ActionListener {
    final static Logger logger = LoggerFactory.getLogger(CollisionManager.class);

	@Override
	public void processComponents() {
		logger.info("processing collision detection");

		for(Collidable collidables : components) {

		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		processComponents();
	}

}
