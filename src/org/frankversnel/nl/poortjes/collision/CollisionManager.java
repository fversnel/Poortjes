package org.frankversnel.nl.poortjes.collision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.ComponentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CollisionManager extends ComponentManager<Collidable> implements ActionListener {
    final static Logger logger = LoggerFactory.getLogger(CollisionManager.class);

	private List<ActionListener> actionListenerList;

	@Override
	public void processComponents() {
		for(Collidable collidable : components) {
			List<Collidable> otherCollidables = new LinkedList<Collidable>(components);
			otherCollidables.remove(collidable);

			for(Collidable otherCollidable : otherCollidables) {
				if(collidable.collidesWith(otherCollidable)) {
					logger.info(collidable + " collides with " + otherCollidable);
					processEvent(new CollisionEvent(this, otherCollidable));
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		processComponents();
	}

	public synchronized void addActionListener(final ActionListener actionEventListener) {
		if(this.actionListenerList == null) {
			this.actionListenerList = new ArrayList<ActionListener>();
		}
		this.actionListenerList.add(actionEventListener);
	}

	public synchronized void removeActionListener(final ActionListener actionEventListener) {
		if((this.actionListenerList != null)
				&& this.actionListenerList.contains(actionEventListener)) {
			this.actionListenerList.remove(actionEventListener);
		}
	}

	private void processEvent(final CollisionEvent collisionEvent) {
		List<ActionListener> actionEventList;

		synchronized(this) {
			if(this.actionListenerList == null) {
				return;
			}
			actionEventList = new ArrayList<ActionListener>(this.actionListenerList);
		}

		for(final ActionListener actionEvent : actionEventList) {
			actionEvent.actionPerformed(collisionEvent);
		}
	}

}
