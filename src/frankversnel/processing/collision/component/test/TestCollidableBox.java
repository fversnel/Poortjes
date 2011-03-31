package frankversnel.processing.collision.component.test;

import org.junit.*;

import static org.junit.Assert.*;

import frankversnel.processing.GameObject;
import frankversnel.processing.collision.component.Collidable;
import frankversnel.processing.collision.component.CollidableBox;
import frankversnel.processing.component.Position;
import frankversnel.processing.component.Size;

public class TestCollidableBox {
	
	@Test
	public void testCollisionWithSelf() {
		GameObject gameObject1 = new GameObject();
		Collidable collidable1 = 
			getCollidable(gameObject1, new Position(gameObject1, 0,0), new Size(gameObject1, 10,10));
		
		assertTrue(collidable1.collidesWith(collidable1));
	}
	
	@Test
	public void testNoCollision() {
		GameObject gameObject1 = new GameObject();
		Collidable collidable1 = 
			getCollidable(gameObject1, new Position(gameObject1, 0,0), new Size(gameObject1, 10,10));
		
		GameObject gameObject2 = new GameObject();
		Collidable collidable2 = 
			getCollidable(gameObject2, new Position(gameObject2, 50,50), new Size(gameObject2, 10,10));
		
		assertFalse(collidable1.collidesWith(collidable2));
	}
	
	public Collidable getCollidable(GameObject gameObject, Position position, Size size) {
		gameObject.addComponent(position);
		gameObject.addComponent(size);
		
		return new CollidableBox(gameObject);
	}

}
