package org.frankversnel.nl.poortjes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.junit.Before;
import org.junit.Test;

public class TestGameObject {
	private GameObject gameObject;

	@Before
	public void setUp() {
		gameObject = new GameObject();
	}

	@Test
	public void testGetComponent() {
		Component expected = new MockComponent(gameObject);
		Component actual = gameObject.getComponent(MockComponent.class);

		assertEquals(expected, actual);
	}

	@Test
	public void testNonExistentComponent() {
		assertNull(gameObject.getComponent(MockComponent.class));
	}

	@Test
	public void testGetAllComponentOfType() {
		new MockComponent(gameObject);
		new MockComponent(gameObject);

		List<MockComponent> actual =
			gameObject.getAllComponentsOfType(MockComponent.class);

		assertTrue(actual.size() == 2);
	}

}
