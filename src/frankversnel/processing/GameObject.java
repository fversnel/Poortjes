package frankversnel.processing;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.ComponentManager;
import frankversnel.processing.component.ComponentNotFoundException;

public class GameObject {
	private static final int FIRST_COMPONENT = 0;
	
	private List<ComponentManager> componentManagers = new LinkedList<ComponentManager>();
	
	@SuppressWarnings("rawtypes")
	private Map<Class, List<Component>> componentStores = new HashMap<Class, List<Component>>();

	public GameObject(ComponentManager ... componentManagers) {
		this.componentManagers.addAll(Arrays.asList(componentManagers));
	}

	public <T extends Component> void addComponent(T component) {
		List<Component> store = componentStores.get(component.getClass());
		
		if(store == null) {
			store = new LinkedList<Component>();
			componentStores.put(component.getClass(), store);
		}
		
		store.add(component);
	}

	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> componentType) {
		List<Component> store = componentStores.get(componentType);
		
		if(store != null && store.size() > 0) {
			return (T) store.get(FIRST_COMPONENT);
		}
		
		return null;
	}
	
	/**
	 * This is the safe variant of the {@link GameObject#getComponent(Class)} method, 
	 * meaning a {@link RuntimeException} will be thrown when a component could not be found.
	 */
	public <T extends Component> T safe_getComponent(Class<T> componentType) {
		T component = getComponent(componentType);
		
		if(component == null) {
			throw new RuntimeException(
					new ComponentNotFoundException(componentType, this));
		}
		
		return component;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> List<T> getAllComponentsOfType(Class<T> componentType) {
		List<T> store = (List<T>) componentStores.get(componentType);

		if(store == null) {
			store = new LinkedList<T>();
		}

		return store;
	}
	
	/**
	 * This is the safe variant of the {@link GameObject#getComponentOfType(Class)} method, 
	 * meaning a {@link RuntimeException} will be thrown the component(s) could not be found.
	 */
	public <T extends Component> List<T> safe_getAllComponentsOfType(Class<T> componentType) {
		List<T> store = getAllComponentsOfType(componentType);
		
		if(store.size() == 0) {
			throw new RuntimeException(
					new ComponentNotFoundException(componentType, this));
		}
		
		return store;
	}

	public boolean removeComponent(Component component) {
		List<Component> store = componentStores.get(component.getClass());
		return store.remove(component);
	}

	public void destroy() {
		for(List<Component> store : componentStores.values()) {
			for(Component component : store) {
				for(ComponentManager manager : componentManagers) {
					manager.removeComponent(component);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "GameObject with components " + this.componentStores.values();
	}

}
