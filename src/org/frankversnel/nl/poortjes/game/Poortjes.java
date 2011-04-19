package org.frankversnel.nl.poortjes.game;

import java.util.Random;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.game.instance.CandyInstance;
import org.frankversnel.nl.poortjes.game.instance.ShepherdInstance;
import org.frankversnel.nl.poortjes.game.instance.PlayerInstance;
import org.frankversnel.nl.poortjes.gameloop.DefaultGameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;
import org.frankversnel.nl.poortjes.gameloop.GameTick;
import org.frankversnel.nl.poortjes.gameloop.RenderGameLoop;
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
	private ProcessingShapeLoader resourceloader;

	private GameLoop gameLoop;

	@Override
	public void setup() {
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
		background(BACKGROUND_COLOR);
		smooth();

		gameLoop = new DefaultGameLoop();

		resourceloader = new ProcessingShapeLoader(this);

		renderManager = new RenderingManager(new Processing2DRenderer(g,
				resourceloader, BACKGROUND_COLOR), new RenderGameLoop());

		CollisionLevel candy = new CollisionLevel(gameLoop);
		CollisionLevel players = new CollisionLevel(gameLoop, candy);
		CollisionLevel enemies = new CollisionLevel(gameLoop, players);

		GameObject player = new PlayerInstance(renderManager, resourceloader,
				"resources/ship.svg", players, gameLoop, SCREEN_WIDTH / 2,
				SCREEN_HEIGHT / 2);
		this.addKeyListener(new Keyboard(player, 'w', 's', 'a', 'd'));

		for (int i = 0; i < 50; i++) {
			int width = new Random().nextInt(SCREEN_WIDTH);
			int height = new Random().nextInt(SCREEN_HEIGHT);
			new CandyInstance(renderManager, resourceloader,
					"resources/candy.svg", candy, gameLoop, width, height);
		}

		new ShepherdInstance(renderManager, resourceloader,
				"resources/shepherd.svg", enemies, gameLoop, 100, 100);

		gameLoop.start();
	}

	public void draw() {
		renderManager.gameTickOccurred(new GameTick(this, (int) (1000 / this.frameRate)));
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "org.frankversnel.nl.poortjes.game.Poortjes" });
	}

}
