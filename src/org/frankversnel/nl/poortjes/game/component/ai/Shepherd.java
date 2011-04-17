package org.frankversnel.nl.poortjes.game.component.ai;

import java.util.List;

import org.frankversnel.nl.poortjes.GameObject;
import org.frankversnel.nl.poortjes.collision.Collision;
import org.frankversnel.nl.poortjes.collision.CollisionListener;
import org.frankversnel.nl.poortjes.collision.component.Collidable;
import org.frankversnel.nl.poortjes.component.Component;
import org.frankversnel.nl.poortjes.component.Speed;
import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.game.component.Player;

public class Shepherd extends Component implements CollisionListener {
	public Shepherd(GameObject gameObject, Collidable collidable) {
		super(gameObject);
		
		collidable.addListener(this);
	}
	
	public void followNearestPlayer(List<Player> players) {
		Player nearestPlayer = getNearestPlayer(players);
		
		if(nearestPlayer != null) {
			Transform nearestPlayerPosition = nearestPlayer.getGameObject().getComponent(Transform.class);
			
			Speed speed = getGameObject().getComponent(Speed.class);
			
			if(nearestPlayerPosition !=  null && speed != null) {
				
			}
		}
	}

	@Override
	public void collision(Collision collision) {
		Player player = collision.getCollidedWith().getComponent(Player.class);
		if (player != null) {
			collision.getCollidedWith().destroy();
		}
	}

	private Player getNearestPlayer(List<Player> players) {
		Transform ownTransform = getGameObject().getComponent(Transform.class);
		Float nearness = null;
		Player nearestPlayer = null;
		for(Player player : players) {
			Transform playerTransform = player.getGameObject().getComponent(Transform.class);
			
			float playerNearness = getNearnessToPlayer(ownTransform, playerTransform);
			if(nearness == null || nearness > playerNearness) {
				nearness = playerNearness;
				nearestPlayer = player;
			}
		}
		
		return nearestPlayer;
	}
	
	private float getNearnessToPlayer(Transform ownTransform, Transform playerTransform) {
		float nearnessX = Math.abs(ownTransform.getPositionX() - playerTransform.getPositionX()); 
		float nearnessY = Math.abs(ownTransform.getPositionY() - playerTransform.getPositionY());
			
		return nearnessX + nearnessY;
	}



}
