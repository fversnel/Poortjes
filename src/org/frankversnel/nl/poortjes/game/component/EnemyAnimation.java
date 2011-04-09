package org.frankversnel.nl.poortjes.game.component;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.animation.Animation;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.gameloop.GameLoop;

/**
 * 
 * @author Martijn Zandvliet
 * @author Frank Versnel
 */
public class EnemyAnimation extends Animation {
	// How strongly the enemy is squished
	private static final float SCALE_FACTOR = 0.33f;
	// The speed of the squishing
	private static final float SCALE_SPEED = 1f;

	// Speed of rotation (rotation should be driven by player location,
	// this is just for show)
	private static final float ROTATION_SPEED = 0.1f;
	// The amount that the enemy rotates left and right
	private static final float ROTATION_AMOUNT = (float) (Math.PI / 16f);

	// Real time use for animation
	private float currentTime = 0f;

	public EnemyAnimation(GameObject gameObject, GameLoop gameLoop) {
		super(gameObject, gameLoop);
	}

	@Override
	public void animate(int timeSinceLastEvent) {
		Transform transform = getGameObject().getComponent(Transform.class);

		if (transform != null) {
			// Animate scale and rotation properties, keeping the animation
			// separated from the object's public properties. This is
			// so the enemy's transform can still be meaningfully
			// manipulated by the rest of the program without the animation
			// overriding it.
			float animScaleX = (float) (1f + Math
					.sin(currentTime * SCALE_SPEED) * SCALE_FACTOR);
			float animScaleY = (float) (1f + Math
					.cos(currentTime * SCALE_SPEED) * SCALE_FACTOR);
			float animRotation = (float) (Math
					.sin(currentTime * ROTATION_SPEED) * ROTATION_AMOUNT);

			// Push object's public transform properties
			// translate(position.x, position.y);
			// scale(scale.x, scale.y);
			// rotate(rotation);
			// pushMatrix();

			// push enemy's extra animated properties
			transform.scale(animScaleX, animScaleY);
			transform.rotate((float) Math.PI / 4f + animRotation);
			// pushMatrix();

			// Draw graphics

			// pop animated properties
			transform.rotate((float) -Math.PI / 4f);
			// popMatrix();

			// pop public transform properties
			// popMatrix();

			float deltaTime = 1f / timeSinceLastEvent;
			currentTime += deltaTime;
		}
	}

}
