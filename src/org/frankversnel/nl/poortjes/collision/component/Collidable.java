package org.frankversnel.nl.poortjes.collision.component;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.CollisionLevel;
import org.frankversnel.nl.poortjes.component.Component;

public abstract class Collidable extends Component {
	private CollisionLevel collisionLevel;

	private List<ActionListener> actionListenerList;

	public Collidable(GameObject gameObject, CollisionLevel collisionLevel) {
		super(gameObject);

		this.collisionLevel = collisionLevel;
		collisionLevel.addComponent(this);
	}

	public abstract void collidesWith(Collidable other);

	public synchronized void addActionListener(
			final ActionListener actionEventListener) {
		if (this.actionListenerList == null) {
			this.actionListenerList = new ArrayList<ActionListener>();
		}
		this.actionListenerList.add(actionEventListener);
	}

	public synchronized void removeActionListener(
			final ActionListener actionEventListener) {
		if ((this.actionListenerList != null)
				&& this.actionListenerList.contains(actionEventListener)) {
			this.actionListenerList.remove(actionEventListener);
		}
	}

	protected void processEvent(final CollisionEvent collisionEvent) {
		List<ActionListener> actionEventList;

		synchronized (this) {
			if (this.actionListenerList == null) {
				return;
			}
			actionEventList = new ArrayList<ActionListener>(
					this.actionListenerList);
		}

		for (final ActionListener actionEvent : actionEventList) {
			actionEvent.actionPerformed(collisionEvent);
		}
	}

	@Override
	public void remove() {
		collisionLevel.removeComponent(this);
	}

}
