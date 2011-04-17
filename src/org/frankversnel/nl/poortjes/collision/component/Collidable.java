package org.frankversnel.nl.poortjes.collision.component;

import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.Collision;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.component.Component;

public abstract class Collidable extends Component {
	private CollisionLevel collisionLevel;

    private final List<CollisionListener> listenerList = new LinkedList<CollisionListener>();

	public Collidable(GameObject gameObject, CollisionLevel collisionLevel) {
		super(gameObject);

		this.collisionLevel = collisionLevel;
		collisionLevel.addComponent(this);
	}

	public abstract void collidesWith(Collidable other);

    public void addListener(CollisionListener listener) {
        listenerList.add(listener);
    }
    
    public void removeListener(CollisionListener listener) {
        listenerList.remove(listener);
    }
    
    protected void fireCollision(Collision collision) {
        for (CollisionListener listener : listenerList) {
        	listener.collision(collision);
        }
    }

	@Override
	public void remove() {
		collisionLevel.removeComponent(this);
	}

}
