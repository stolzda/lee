package ch.ksimlee.it.spaceinvaders;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import ch.ksimlee.it.spaceinvaders.log.Log;
import ch.ksimlee.it.spaceinvaders.objects.Alien;
import ch.ksimlee.it.spaceinvaders.objects.RenderObject;
import ch.ksimlee.it.spaceinvaders.objects.Spaceship;

public class Game implements Runnable {
	
	private static final int ACTIONS_PER_SECOND = 30;
	
	/** A list of all objects that can be rendered. */
	private final Set<RenderObject> objectsToRender = new HashSet<RenderObject>();
	
	private final Set<RenderObject> objectsToAdd = new HashSet<RenderObject>();

	public Set<RenderObject> getObjectsToAdd() {
		return objectsToAdd;
	}

	public Set<RenderObject> getObjectsToRemove() {
		return objectsToRemove;
	}

	private final Set<RenderObject> objectsToRemove = new HashSet<RenderObject>();

	
	/** The handler that should receive the user input. */
	private final InputHandler inputHandler = new InputHandler();
	
	private Spaceship spaceship;
	
	public Game() {
		
		Log.info("Starting a game with " + ACTIONS_PER_SECOND + " actions/second.");
		
		initialize();
	}

	/**
	 * Initialize a new game.
	 */
	private void initialize() {
		
		// Create the spaceship.
		spaceship = new Spaceship(200, 200);
		
		// Add the spaceship to the list of renderable objects.
		objectsToRender.add(spaceship);
		
		for (int i = 0; i < 500; i += 100) {
			objectsToRender.add(new Alien(i, 100));
		}
		
		Log.info("Game initialized.");
	}
	
	/**
	 * Reset the game, i.e., perform a restart.
	 */
	private void reset() {

		// Clear all objects from the game.
		objectsToRender.clear();
		
		// Reinitialize the game.
		initialize();
	}

	@Override
	public void run() {
		
		while (true) {
			// This loop goes forever, since we don't want our game
			// logic to stop.
			
			// Check if the game should be resetted.
			if (inputHandler.isKeyPressed(KeyEvent.VK_R)) {
				reset();
			}
			
			
			// Update all game objects.
			for (RenderObject object : objectsToRender) {
				object.update(this);
			}
			
			objectsToRender.removeAll(objectsToRemove);
			objectsToRender.addAll(objectsToAdd);
			
			objectsToAdd.clear();
			objectsToRemove.clear();
			
			// Update the input state.
			inputHandler.updatedReleasedKeys();
			
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

	public InputHandler getInputHandler() {
		return inputHandler;
	}
}
