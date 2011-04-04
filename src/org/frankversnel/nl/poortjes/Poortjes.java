package org.frankversnel.nl.poortjes;

import java.io.FileNotFoundException;

import org.frankversnel.nl.poortjes.collision.CollisionManager;
import org.frankversnel.nl.poortjes.dummy.CandyInstance;
import org.frankversnel.nl.poortjes.dummy.PlayerWithShape;
import org.frankversnel.nl.poortjes.gameloop.DefaultGameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.input.Keyboard;
import org.frankversnel.nl.poortjes.rendering.Processing2DRenderer;
import org.frankversnel.nl.poortjes.rendering.RenderingManager;
import org.frankversnel.nl.poortjes.resourceloading.ProcessingShapeLoader;

import processing.core.PApplet;

public class Poortjes extends PApplet {
	private static final long serialVersionUID = 9178782595328986939L;

	private static final int SCREEN_WIDTH = 400;
	private static final int SCREEN_HEIGHT = 400;
	private static final int BACKGROUND_COLOR = 0;

	private RenderingManager renderManager;
	private CollisionManager collisionManager;

	private GameLoop gameLoop;

	@Override
	public void setup() {
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
		background(BACKGROUND_COLOR);
		smooth();

		ProcessingShapeLoader shapeLoader = new ProcessingShapeLoader(this);
		String playerShapeId;
		String candyId;
		try {
			playerShapeId = shapeLoader.load("resources/drawing.svg");
			candyId = shapeLoader.load("resources/candy.svg");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		renderManager = new RenderingManager(new Processing2DRenderer(g,
				shapeLoader));

		collisionManager = new CollisionManager();

		gameLoop = new DefaultGameLoop();
		gameLoop.addActionListener(collisionManager);
		gameLoop.start();

		GameObject player = new PlayerWithShape(renderManager,
				collisionManager, gameLoop, 300, 200, playerShapeId);
		this.addKeyListener(new Keyboard(player, 'w', 's', 'a', 'd'));

		new PlayerWithShape(renderManager, collisionManager, gameLoop, 300,
				300, playerShapeId);
		new CandyInstance(renderManager, collisionManager, gameLoop, 200, 100,
				candyId);
	}

	@Override
	public void draw() {
		clearScreen();
		renderManager.processComponents();
	}

	private void clearScreen() {
		background(BACKGROUND_COLOR);
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"org.frankversnel.nl.poortjes.Poortjes" });
	}

}