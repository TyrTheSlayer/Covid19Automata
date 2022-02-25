/*package GridVisualization;

import java.awt.Color;

public class Tile {
	private int x;
	private int y;
	public int age;
	private Color color;
	public static Color gridColor = Color.BLACK;
	public static Color youngColor = new Color(200, 200, 210);
	public static Color oldColor = new Color(100, 100, 110);
	public static int maxAge=10;
	
	public Tile(int x, int y) {
		changeCoordinates(x, y);
	}
	
	public void setBackground(Color color) {
		this.color = color;
	}
	
	public Color getBackgroundColor() {
		return this.color;
	}
	
	// -1 for dead
	public void updateMark(int age) {
		this.age = age;
		this.updateColor();
	}
	
	public void updateColor() {
		if (age == -1)
			setBackground(gridColor);
		else if (age >= maxAge)
			setBackground(oldColor);
		else {
			float pToMin = (float)1.0-(float)age/maxAge;
			setBackground(ColorUtility.getGradient(youngColor, oldColor, pToMin));
		}
	}
	
	public void changeCoordinates(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}*/

package GridVisualization;

import custom_classes.*;

public class Tile {
	private Person occupant;
	private Building entranceTo;
	private boolean accessible;
	private int x;
	private int y;

	/**
	 * Makes a new tile with the given x and y coordinates
	 *
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public Tile(int x, int y) {
		this.occupant = null;
		this.entranceTo = null;
		this.accessible = true;
		changeCoordinates(x, y);
	}

	//Getters
	/**
	 * Gets the x location of the tile
	 *
	 * @return The x location of the tile
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y location of the tile
	 *
	 * @return The y location of the tile
	 */
	public int getY() {
		return y;
	}

	//Methods
	/**
	 * Sets the coordinate of the tile
	 *
	 * @param x The x location
	 * @param y The y location
	 */
	public void changeCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

