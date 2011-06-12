package org.frankversnel.nl.poortjes.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameTick;
import org.frankversnel.nl.poortjes.gameloop.GameTickListener;

public abstract class ComponentManager<T extends Component>
		implements GameTickListener {
	private List<T> components = Collections
			.synchronizedList(new ArrayList<T>());

	public ComponentManager(GameLoop gameLoop) {
		gameLoop.addListener(this);
	}

	public void addComponent(T component) {
		components.add(component);
	}

	public List<T> getComponents() {
		List<T> componentsCopy = new ArrayList<T>(components.size());
		componentsCopy.addAll(components);
		return componentsCopy;
	}

	public boolean removeComponent(Component component) {
		return components.remove(component);
	}

	@Override
	public void gameTickOccurred(GameTick gameTick) {
		processComponents();
	}

	public abstract void processComponents();

}
