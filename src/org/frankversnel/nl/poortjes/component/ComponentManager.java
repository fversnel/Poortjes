package org.frankversnel.nl.poortjes.component;

import java.util.LinkedList;
import java.util.List;

public abstract class ComponentManager <T extends Component> {
	private List<T> components = new LinkedList<T>();
	
	public void addComponent(T component) {
		components.add(component);
	}
	
	public List<T> getComponents() {
		return new LinkedList<T>(components);
	}
	
	public abstract void processComponents();
	
	public boolean removeComponent(Component component) {
		return components.remove(component);
	}
	
}