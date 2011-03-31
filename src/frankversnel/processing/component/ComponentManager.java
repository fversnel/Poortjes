package frankversnel.processing.component;

import java.util.LinkedList;
import java.util.List;

public abstract class ComponentManager <T extends Component> {
	protected List<T> components = new LinkedList<T>();
	
	public void addComponent(T component) {
		components.add(component);
	}
	
	public abstract void processComponents();
	
	public boolean removeComponent(Component component) {
		return components.remove(component);
	}
	
}