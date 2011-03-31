package frankversnel.processing.dummy;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Angle;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;
import frankversnel.processing.component.Speed;
import frankversnel.processing.gameloop.GameLoop;
import frankversnel.processing.movement.Moveable;
import frankversnel.processing.rendering.RenderingManager;
import frankversnel.processing.rendering.component.Color;
import frankversnel.processing.rendering.component.DrawablePolygon;

public class PlayerWithPolygon extends GameObject {

	public PlayerWithPolygon(RenderingManager renderManager, GameLoop gameLoop) {
		super(renderManager);
		
		new Position(this, 100, 100);
		Color.red(this);
		Size size = new Size(this, 20, 20);
		new Angle(this, 50);
		new Speed(this, 0.1f);
		Moveable moveAble = new Moveable(this);
		gameLoop.addActionListener(moveAble);
		
		DrawablePolygon playerLooks = DrawablePolygon.square(this, size);
		renderManager.addComponent(playerLooks);
	}

}
