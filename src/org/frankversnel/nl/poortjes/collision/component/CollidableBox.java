package org.frankversnel.nl.poortjes.collision.component;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.component.Transform;

public class CollidableBox extends Collidable {
	public CollidableBox(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public boolean collidesWith(Collidable other) {
		Area ours = getBoundingBox(this);
		Area theirs = getBoundingBox(other);
		
		return ours.intersects(theirs.getBounds2D());
	}
	
	private Area getBoundingBox(Collidable collidable) {
		Transform transform = collidable.getGameObject().safe_getComponent(Transform.class);
		
		Area boundingBox = new Area(new Rectangle2D.Float(0, 0, 1, 1));
		boundingBox.transform(transform.getAffineTransform());
		return boundingBox;
	}

}
