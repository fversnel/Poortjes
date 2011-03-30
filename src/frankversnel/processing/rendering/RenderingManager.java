package frankversnel.processing.rendering;

import java.util.LinkedList;
import java.util.List;

import frankversnel.processing.component.Component;
import frankversnel.processing.component.ComponentManager;
import frankversnel.processing.rendering.component.Drawable;

public class RenderingManager implements ComponentManager {
	private Renderer renderer;
	private List<Drawable> drawables =  new LinkedList<Drawable>();
	
	public RenderingManager(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public void addDrawable(Drawable renderable) {
		drawables.add(renderable);
	}
	
	public void drawAll() {
		for(Drawable drawable : drawables) {
			drawable.draw(renderer);
		}
	}

	@Override
	public boolean removeComponent(Component component) {
		return drawables.remove(component);
	}

}
