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
	private Building entranceTo = null;
	private boolean accessible;
	private boolean selected = false;
	private boolean isEntrance = false;
	private boolean isExit = false;
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

	/**
	 * A boolean that determines if this tile is accessible
	 * @return Whether this tile is in fact accessible
	 */
	public boolean isAccessible() { return this.accessible; }

	/**
	 * A getter for the building it's an entrance to
	 * @return The building, null o/w
	 */
	public Building isEntranceTo() {
		return this.entranceTo;
	}

	//Setters
	/**
	 * Sets the entrance status of the tile
	 *
	 * @param entrance The entrance status
	 */
	public void setEntrance(boolean entrance) {
		isEntrance = entrance;
	}

	/**
	 * Sets the entrance status of the tile
	 *
	 * @param exit The entrance status
	 */
	public void setExit(boolean exit) {
		isExit = exit;
	}

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
	 * Sets the tile to have not occupant. Should only be called on tile that currently have an occupant.
	 */
	public void clearOccupant() {
		this.occupant = null;
		this.setAccessible(true);
	}

	/**
	 * Gives a person to this tile, with the person coming from the given tile
	 *
	 * @param tile The tile to pull the person from
	 */
	public void takePerson(Tile tile) {
		if (tile == this) return;
		if (tile.occupant == null) return;
		this.occupant = tile.occupant;
		this.occupant.setPosition(this.x, this.y);
		this.setAccessible(false);
		tile.clearOccupant();
		if (this.entranceTo != null) {
			this.entranceTo.enter(this.occupant);
		}
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
		if(this.isExit)
			canvas.setColor(Color.decode("#D55E00"));
		else if (this.isEntrance)
			canvas.setColor(Color.decode("#009E73"));
		else if (!this.accessible && this.occupant == null)
			canvas.setColor(Color.LIGHT_GRAY);
		else
			canvas.setColor(Color.DARK_GRAY);
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
}

