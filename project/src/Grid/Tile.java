/**
 * @author Wesley Camphouse, Samuel Nix
 *
 * Creates tiles for the simulation
 */

package Grid;

import DataObjects.Person;
import DataObjects.Status;

import java.awt.*;

public class Tile {
	private Person occupant;
	private Building entranceTo;
	private boolean accessible;
	private boolean selected = false;
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

	/**
	 * Gets the occupant of the tile
	 *
	 * @return The occupant of the tile
	 */
	public Person getOccupant() {
		return occupant;
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
	 * Sets the tile to have not occupant. Should only be called on tile that currently have an occupant.
	 */
	public void clearOccupant() {
		this.occupant = null;
		this.setAccessible(true);
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
	public void takePerson(Tile tile){
		if (tile == this) return;
		if (tile.occupant == null) return;
		this.occupant = tile.occupant;
		this.occupant.setPosition(this.x, this.y);
		this.setAccessible(false);
		tile.clearOccupant();
	}

	public String toString() {
		String s = "(" + this.x + ", " + this.y + ")\n";
		s += "Accessible: " + this.isAccessible() + "\n";
		s += "Occupant: " + this.occupant + "\n";
		return s;
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
		else if (this.selected)
			canvas.setColor(Color.WHITE);
		else
			canvas.setColor(Color.LIGHT_GRAY);
		canvas.fillRect(size * this.x, size * this.y, size, size);

		//If there's an occupant, have them draw themselves
		if(this.occupant != null) {
			//If they're dead, clear this tile
			if(this.occupant.getStatus() == Status.DEAD) {
				this.clearOccupant();
			}
			//Otherwise, draw
			else
				this.occupant.draw(size, canvas);
		}

		//Restore the old color
		canvas.setColor(oldCol);
	}
	public void highlightTile() {
		this.selected = true;
	}
	public void unhighlightTile() {
		this.selected = false;
	}
}

