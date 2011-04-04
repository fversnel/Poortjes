package org.frankversnel.nl.poortjes.collision.component;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.CollisionManager;
import org.frankversnel.nl.poortjes.component.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollidableBox extends Collidable {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public CollidableBox(GameObject gameObject,
			CollisionManager collisionManager) {
		super(gameObject, collisionManager);
	}

	@Override
	public void collidesWith(Collidable theirs) {
		Transform ourTransform = getGameObject().getComponent(Transform.class);
		Transform theirTransform = theirs.getGameObject().getComponent(
				Transform.class);

		if (ourTransform != null && theirTransform != null) {
			Area ourArea = getBoundingBox(ourTransform);
			Area theirArea = getBoundingBox(theirTransform);

			boolean collision = ourArea.intersects(theirArea.getBounds2D());

			if (collision) {
				logger.info(getGameObject() + " collides with "
						+ theirs.getGameObject());

				processEvent(new CollisionEvent(this, theirs));
			}
		}
	}

	private Area getBoundingBox(Transform transform) {
		Area boundingBox = new Area(new Rectangle2D.Float(0, 0, 1, 1));
		boundingBox.transform(transform.getAffineTransform());
		return boundingBox;
	}

}
