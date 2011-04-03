package org.frankversnel.nl.poortjes.test;

import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.frankversnel.nl.poortjes.component.Speed;
import org.junit.*;


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
