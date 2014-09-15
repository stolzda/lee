package ch.ksimlee.it.spaceinvaders;

import ch.ksimlee.it.spaceinvaders.log.Log;
import ch.ksimlee.it.spaceinvaders.log.Log.Level;

public class Main {

	public static void main(String[] args) {
		
		for (int i = 0; i < 4; i++) {
			
			// Set the Log Level.
			Level level = Log.Level.values()[i];
			System.out.println("Setting log level for testing to: " + level);
			Log.setLevel(level);
			
			// Execute the test function with the current settings of
			// the logger.
			myFunction(10);
		}
		
	}
	
	public static void myFunction(int x) {
		
		Log.debug("Entering function myFunction");
		
		int y = x * x;
		Log.info("Result: " + y);
		
		Log.warning("This logs a warning");
		
		Log.error("This logs an error");
	}

}
