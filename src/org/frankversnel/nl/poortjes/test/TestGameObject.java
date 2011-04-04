package org.frankversnel.nl.poortjes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.component.Speed;
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

}
