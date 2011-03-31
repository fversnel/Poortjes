package frankversnel.processing.rendering;

import frankversnel.processing.component.ComponentManager;
import frankversnel.processing.rendering.component.Drawable;

public class RenderingManager extends ComponentManager<Drawable> {
	private Renderer renderer;
	
	public RenderingManager(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void processComponents() {
		for(Drawable drawable : components) {
			drawable.draw(renderer);
		}
	}

}
