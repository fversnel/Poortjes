package frankversnel.processing.component.renderer;

import java.util.List;

import frankversnel.processing.component.ComponentManager;
import frankversnel.processing.component.PositionComponent;
import frankversnel.processing.component.SizeComponent;
import frankversnel.processing.gameobjects.GameObject;

public class RendererManager implements ComponentManager {

	@Override
	public void processComponents(List<GameObject> gameObjects) {
		for(GameObject object : gameObjects) {
			Renderer renderer = (Renderer)object.getComponent(Renderer.class);
			PositionComponent objectPosition = (PositionComponent) object.getComponent(PositionComponent.class);
			SizeComponent objectSize = (SizeComponent) object.getComponent(SizeComponent.class);
			renderer.draw(objectPosition, objectSize);
		}
	}

}
