package ch.ksimlee.it.spaceinvaders.objects;

import java.awt.event.KeyEvent;

import ch.ksimlee.it.spaceinvaders.InputHandler;

/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;
	
	private int speed = 10;

	public Spaceship(int x, int y) {
		super(x, y, zIndex, FILENAME);
	}

	@Override
	public void update(InputHandler currentInput) {
		
		// Check if we need to move left.
		if (currentInput.isKeyPressed(KeyEvent.VK_A) ||
				currentInput.isKeyPressed(KeyEvent.VK_LEFT)) {
			
			move(-speed, 0);
		}
		
		// Check if we need to move right.
		if (currentInput.isKeyPressed(KeyEvent.VK_D) ||
				currentInput.isKeyPressed(KeyEvent.VK_RIGHT)) {
			
			move(speed, 0);
		}
	}

}
