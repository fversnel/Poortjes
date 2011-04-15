package org.frankversnel.nl.poortjes.collision.component.test;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Size;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.collision.component.CollidableBox;

import org.junit.*;

import static org.mockito.Mockito.*;

public class TestCollidableBox {
	private CollisionLevel collisionLevel;
	private CollisionListener collisionListener;

	@Before
	public void setUp() {
		collisionLevel = mock(CollisionLevel.class);
		collisionListener = mock(CollisionListener.class);
	}

	@Test
	public void testCollision() {
		Collidable box1 = createCollidableBox(0, 0, new Size(25, 10));
		Collidable box2 = createCollidableBox(20, 5, new Size(10, 10));

		box1.addActionListener(collisionListener);
		box1.collidesWith(box2);
		verify(collisionListener).actionPerformed(any(CollisionEvent.class));
	}

	@Test
	public void testNoCollision() {
		Collidable box1 = createCollidableBox(0, 0, new Size(10, 10));
		Collidable box2 = createCollidableBox(20, 5, new Size(10, 10));

		box1.addActionListener(collisionListener);
		box1.collidesWith(box2);
		verify(collisionListener, never()).actionPerformed(any(CollisionEvent.class));
	}

	private Collidable createCollidableBox(float positionX, float positionY, Size size) {
		GameObject mockGameObject = mock(GameObject.class);
		Transform transform = new Transform(mockGameObject, positionX, positionY, size);
		when(mockGameObject.getComponent(Transform.class)).thenReturn(transform);

		return new CollidableBox(mockGameObject, collisionLevel);
	}

}
