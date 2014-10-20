package ch.ksimlee.it.spaceinvaders;

import java.util.HashSet;
import java.util.Set;

import ch.ksimlee.it.spaceinvaders.log.Log;
import ch.ksimlee.it.spaceinvaders.objects.RenderObject;
import ch.ksimlee.it.spaceinvaders.objects.Spaceship;

public class Game implements Runnable {
	
	private static final int ACTIONS_PER_SECOND = 10;
	
	/** A list of all objects that can be rendered. */
	private final Set<RenderObject> objectsToRender = new HashSet<RenderObject>();
	
	private final Spaceship spaceship;
	
	public Game() {
		
		Log.info("Starting a game with " + ACTIONS_PER_SECOND + " actions/second.");
		
		// Create the spaceship.
		spaceship = new Spaceship(200, 200);
		
		// Add the spaceship to the list of renderable objects.
		objectsToRender.add(spaceship);
		
		Log.info("Game initialized.");
	}

	@Override
	public void run() {
		
		while (true) {
			// This loop goes forever, since we don't want our game
			// logic to stop.
			
			// TODO: Add game mechanics here.
			
			// XXX: Example
			spaceship.setX(spaceship.getX() + 5);
			if (spaceship.getX() >  400) {
				spaceship.setX(200);
			}
			
			// Delay the next action (iteration of the loop).
			try {
				Thread.sleep((long) (1000.0f/ACTIONS_PER_SECOND));
				
			} catch (InterruptedException e) {
				// We were interrupted, exit the game loop.
				
				Log.error("Caught interruption exception, exiting game loop.");
				e.printStackTrace();
				break;
			}
			
		}
		
	}
	
	public Set<RenderObject> getObjectsToRender() {
		return objectsToRender;
	}

}
