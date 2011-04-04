package org.frankversnel.nl.poortjes.collision.component;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.CollisionEvent;
import org.frankversnel.nl.poortjes.collision.CollisionManager;
import org.frankversnel.nl.poortjes.component.Component;

public abstract class Collidable extends Component {
	private CollisionManager collisionManager;

	private List<ActionListener> actionListenerList;

	public Collidable(GameObject gameObject, CollisionManager collisionManager) {
		super(gameObject);

		this.collisionManager = collisionManager;
		collisionManager.addComponent(this);
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
		collisionManager.removeComponent(this);
	}

}
