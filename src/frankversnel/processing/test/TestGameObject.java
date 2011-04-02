package frankversnel.processing.test;

import java.util.List;

import org.junit.*;

import frankversnel.processing.GameObject;
import frankversnel.processing.component.Component;
import frankversnel.processing.component.ComponentManager;
import frankversnel.processing.component.Speed;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestGameObject {
	private GameObject gameObject;
	private ComponentManager componentManager;
	
	@Before
	public void setUp() {
		componentManager = mock(ComponentManager.class);
		gameObject = new GameObject(componentManager);
	}
	
	@Test
	public void testGetComponent() {
		Component expected = new Speed(gameObject, 0, 0);
		Component actual = gameObject.getComponent(Speed.class);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonExistentComponent() {
		assertNull(gameObject.getComponent(Speed.class));
	}
	
	@Test
	public void testGetAllComponentOfType() {
		new Speed(gameObject, 0, 0);
		new Speed(gameObject, 0, 0);
		
		List<Speed> actual = gameObject.getAllComponentsOfType(Speed.class);
		
		assertTrue(actual.size() == 2);
	}
	
	@Test
	public void testRemoveComponent() {
		Component component = new Speed(gameObject, 0,0);
		gameObject.removeComponent(component);
		
		assertNull(gameObject.getComponent(Speed.class));
	}

}
