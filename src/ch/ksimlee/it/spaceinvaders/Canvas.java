package ch.ksimlee.it.spaceinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import ch.ksimlee.it.spaceinvaders.log.Log;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	// This variable stores the size of the canvas (in pixel).
	private Dimension SIZE = new Dimension(640, 480);

	// This variable stores with how many frames per second (FPS) the canvas
	// should be redrawn.
	private static final int FPS = 25;

	public Canvas() {
		setPreferredSize(SIZE);

		Log.info("Initialized canvas.");

		/*
		 * The class timer (from swing) can be used to call a function
		 * periodically. The first argument specifies the frequency in
		 * milliseconds with which the function will be called, and the second
		 * argument an object of type "ActionListener" from which the function
		 * "actionPerformed" will be called with the specified frequency.
		 */
		Timer timer = new Timer((int) (1000.0 / FPS), new ActionListener() {

			// This function is called with the specified frequency.
			@Override
			public void actionPerformed(ActionEvent e) {
				// Repaint the canvas object.
				Canvas.this.repaint();
			}
		});

		// As the timer should immediately start to work, we start it here.
		timer.start();

		Log.info("Drawing timer started.");
	}

	// This function draws this canvas (it draws the JPanel).
	public void paintComponent(Graphics g) {

		Log.debug("Painting the canvas.");

		// Let the swing framework do it's drawing of the JPanel first.
		super.paintComponent(g);

		// The following two commands draw the background:

		// Set the color of the "pen". This color will be used in the following
		// drawing commands.
		g.setColor(Color.white);

		// Draw a rectangle with the size of the canvas. Therefore, this draws a
		// "background".
		g.fillRect(0, 0, SIZE.width, SIZE.height);

		// TODO: Add more drawing commands here.

	}

}
