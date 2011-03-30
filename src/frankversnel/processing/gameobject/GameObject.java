package frankversnel.processing.gameobject;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.ComponentManager;

public class GameObject {
	private List<ComponentManager> componentManagers = new LinkedList<ComponentManager>();
	
	@SuppressWarnings("rawtypes")
	private Map<Class, Component> componentStores = new HashMap<Class, Component>();

	public GameObject(ComponentManager ... componentManagers) {
		this.componentManagers.addAll(Arrays.asList(componentManagers));
	}

	public <T extends Component> void addComponent(T component) {
		componentStores.put(component.getClass(), component);
	}


	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> componentType) {
		return (T)componentStores.get(componentType);
	}

	public void destroy() {
		for(Component component : componentStores.values()) {
			for(ComponentManager manager : componentManagers) {
				manager.removeComponent(component);
			}
		}
	}

}
