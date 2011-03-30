package frankversnel.processing.gameobject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.ComponentManager;

public class GameObject {
	private List<ComponentManager> componentManagers = new LinkedList<ComponentManager>();
	private List<Component> components = new LinkedList<Component>();

	public GameObject(ComponentManager ... componentManagers) {
		this.componentManagers.addAll(Arrays.asList(componentManagers));
	}

	public void addComponent(Component component) {
		this.components.add(component);
	}

	public void destroy() {
		for(Component component : components) {
			for(ComponentManager manager : componentManagers) {
				manager.removeComponent(component);
			}
		}
	}

}
