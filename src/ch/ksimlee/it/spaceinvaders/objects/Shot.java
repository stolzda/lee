package ch.ksimlee.it.spaceinvaders.objects;

import ch.ksimlee.it.spaceinvaders.Game;

public class Shot extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "shot.png";
	
	private static final int zIndex = 100;
	
	private int speed = 10;

	public Shot(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	public Shot(Spaceship spaceship) {
		this(0,0);
		
		x = spaceship.getCenterX() - getWidth() / 2;
		y = spaceship.y - getHeight();
	}

	@Override
	public void update(Game game) {
		
		RenderObject collision = move(0, -speed, game.getObjectsToRender());
		
		if (collision != null) {
			if (collision instanceof Alien) {
				game.getObjectsToRemove().add(collision);
				game.getObjectsToRemove().add(this);
				
				game.getObjectsToAdd().add(new Explosion(collision));
			}
		}
		
		if (y <= -getHeight()) {
			// The shot is outside of the visible area.
			game.getObjectsToRemove().add(this);
		}
	}
}

