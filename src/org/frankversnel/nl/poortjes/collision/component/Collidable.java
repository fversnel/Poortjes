package org.frankversnel.nl.poortjes.collision.component;

import javax.swing.event.EventListenerList;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.Collision;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.component.Component;

public abstract class Collidable extends Component {
	private CollisionLevel collisionLevel;

    private final EventListenerList listenerList = new EventListenerList();

	public Collidable(GameObject gameObject, CollisionLevel collisionLevel) {
		super(gameObject);

		this.collisionLevel = collisionLevel;
		collisionLevel.addComponent(this);
	}

	public abstract void collidesWith(Collidable other);

    public void addListener(CollisionListener listener) {
        listenerList.add(CollisionListener.class, listener);
    }
    
    public void removeListener(CollisionListener listener) {
        listenerList.remove(CollisionListener.class, listener);
    }
    
    protected void fireCollision(Collision collision) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==CollisionListener.class) {
                ((CollisionListener)listeners[i+1]).collision(collision);
            }
        }
    }

	@Override
	public void remove() {
		collisionLevel.removeComponent(this);
	}

}
