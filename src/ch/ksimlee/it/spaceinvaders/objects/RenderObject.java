package ch.ksimlee.it.spaceinvaders.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import ch.ksimlee.it.spaceinvaders.Canvas;
import ch.ksimlee.it.spaceinvaders.InputHandler;

/**
 * This class can be extended by classes that can render themselves on the
 * {@link Canvas}.
 */
public abstract class RenderObject implements Comparable<RenderObject> {
	
	/** Should the bounding boxes of objects be drawn? */
	public static boolean SHOW_BOUNDING_BOX = true;
	
	/** The X coordinate of this render object. */
	protected int x;
	
	/** The Y coordinate of this render object. */
	protected int y;
	
	/** Can other objects collide with this object? */
	protected boolean hasCollision = false;
	
	/**
	 * The zIndex is responsible for how much in front the object is drawn. The
	 * higher this index is, the more on top of the image will the object be
	 * drawn.
	 */
	protected int zIndex;
	
	/**
	 * Create a new render object with coordinates.
	 * 
	 * @param x
	 *            The initial X coordinate.
	 * @param y
	 *            The initial Y coordinate.
	 * @param zIndex
	 *            The initial zIndex of the object.
	 * @param collision
	 *            Can other objects collide with this object?
	 */
	public RenderObject(int x, int y, int zIndex, boolean collision) {
		this.x = x;
		this.y = y;
		this.zIndex = zIndex;
		this.hasCollision = collision;
	}
	
	/**
	 * Update this object based on the current user input.
	 * 
	 * @param currentInput
	 *            The current user input.
	 * @param allObjects
	 *            All objects that currently exist.
	 */
	public void update(InputHandler currentInput, Set<RenderObject> allObjects) {
		// Default: Do nothing
	}
	
	/**
	 * Move this object.
	 * 
	 * @param dx
	 *            Delta x to move.
	 * @param dy
	 *            Delta y to move.
	 * @param allObjects
	 *            All objects that currently exist.
	 */
	public void move(int dx, int dy, Set<RenderObject> allObjects) {
		this.x += dx;
		this.y += dy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return false;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	/**
	 * Internal function to render objects.
	 * 
	 * @param g
	 *            The graphics context to render on.
	 */
	public final void renderInternal(Graphics g) {

		// Perform the actual rendering.
		render(g);

		// Perform the debug rendering.
		
		if (SHOW_BOUNDING_BOX) {

			// Store the current color.
			Color color = g.getColor();

			g.setColor(Color.magenta);
			g.drawRect(x, y, getWidth(), getHeight());

			// Restore the color.
			g.setColor(color);
		}
	}
	
	/**
	 * Render this object on the Canvas' graphic area.
	 * 
	 * @param g
	 *            The graphics object to render itself on.
	 */
	public abstract void render(Graphics g);
	
	@Override
	public int compareTo(RenderObject o) {
		return zIndex - o.zIndex;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

}
