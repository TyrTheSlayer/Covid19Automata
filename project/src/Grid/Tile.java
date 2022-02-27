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

package Grid;

import custom_classes.Building;
import custom_classes.Person;

import java.awt.*;

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

	//Setters
	/**
	 * Sets the tile to be the entrance to the given building
	 *
	 * @param building The building to be and entrance to
	 */
	public void setEntranceTo(Building building) {
		this.entranceTo = building;
	}

	/**
	 * Sets the tile to hold the given person
	 *
	 * @param person The person to hold
	 */
	public void setOccupant(Person person) {
		this.occupant = person;
		this.occupant.setPosition(this.x, this.y);
		this.setAccessible(false);
	}

	/**
	 * Sets whether or not the tile is accessible
	 *
	 * @param accessible True if it should be, false otherwise
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public boolean isAccessible() { return this.accessible; }
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

	/**
	 * Gives a person to this tile, with the person coming from the given tile
	 *
	 * @param tile The tile to pull the person from
	 */
	public void takePerson(Tile tile) {
		this.occupant = tile.occupant;
		tile.occupant = null;
		this.occupant.setPosition(this.x, this.y);
		this.setAccessible(false);
		tile.setAccessible(true);
	}

	/**
	 * Draws the tile at it's location, given it's width and height, as well as the graphics environment
	 *
	 * @param size The size
	 * @param canvas The graphics environment
	 */
	public void draw(int size, Graphics2D canvas) {
		//Store the old color
		Color oldCol = canvas.getColor();

		//Draw the fill of the tile
		if(this.accessible || this.occupant != null)
			canvas.setColor(Color.DARK_GRAY);
		else
			canvas.setColor(new Color(99, 10, 10));
		canvas.fillRect(size * this.x, size * this.y, size, size);

		//If there's an occupant, have them draw themselves
		if(this.occupant != null)
			this.occupant.draw(size, canvas);

		//Restore the old color
		canvas.setColor(oldCol);
	}
}

