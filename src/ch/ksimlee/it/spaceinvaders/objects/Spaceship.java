package ch.ksimlee.it.spaceinvaders.objects;

import java.awt.event.KeyEvent;

import ch.ksimlee.it.spaceinvaders.Game;

/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;
	
	private int speed = 10;
	
	private static final int shotDelay = 5;
	
	private int shotTimeout = 0; 

	public Spaceship(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	@Override
	public void update(Game game) {
		
		// Check if we need to move left.
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_A) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_LEFT)) {
			
			move(-speed, 0, game.getObjectsToRender());
		}
		
		// Check if we need to move right.
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_D) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_RIGHT)) {
			
			move(speed, 0, game.getObjectsToRender());
		}
		
		// Check if we need to shoot.
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_SPACE) &&
				shotTimeout == 0) {
			
			game.getObjectsToAdd().add(new Shot(this));
			
			shotTimeout = shotDelay;
		}
		
		if (shotTimeout > 0) {
			shotTimeout--;
		}
	}

}
