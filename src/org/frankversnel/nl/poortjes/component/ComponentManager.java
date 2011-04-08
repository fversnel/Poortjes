package org.frankversnel.nl.poortjes.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ComponentManager<T extends Component> {
	private List<T> components = Collections
			.synchronizedList(new ArrayList<T>());

	public void addComponent(T component) {
		components.add(component);
	}

	public List<T> getComponents() {
		List<T> componentsCopy = new ArrayList<T>(components.size());
		componentsCopy.addAll(components);
		return componentsCopy;
	}

	public abstract void processComponents();

	public boolean removeComponent(Component component) {
		return components.remove(component);
	}

}