package org.frankversnel.nl.poortjes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.frankversnel.nl.poortjes.component.Component;

public class GameObject {
	private static final int FIRST_COMPONENT = 0;

	@SuppressWarnings("rawtypes")
	private Map<Class, List<Component>> componentStores = Collections
			.synchronizedMap(new HashMap<Class, List<Component>>());

	public <T extends Component> void addComponent(T component) {
		List<Component> store = componentStores.get(component.getClass());

		if (store == null) {
			store = Collections.synchronizedList(new ArrayList<Component>());
			componentStores.put(component.getClass(), store);
		}

		store.add(component);
	}

	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> componentType) {
		List<Component> store = componentStores.get(componentType);

		if (store != null && store.size() > 0) {
			return (T) store.get(FIRST_COMPONENT);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends Component> List<T> getAllComponentsOfType(
			Class<T> componentType) {
		List<T> store = (List<T>) componentStores.get(componentType);

		if (store == null) {
			store = new ArrayList<T>();
		}

		return store;
	}

	public void destroy() {
		for (List<Component> store : componentStores.values()) {
			for (Component component : new LinkedList<Component>(store)) {
				component.remove();
				store.remove(component);
			}
		}
	}

	@Override
	public String toString() {
		return "GameObject (" + hashCode() + ") with components "
				+ this.componentStores.values();
	}

}
