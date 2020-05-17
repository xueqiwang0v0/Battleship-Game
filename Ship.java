package battleship;

/**
 * This abstract class describes the characteristics common to all ships.
 * It includes basic parameters of a ship and methods that manipulate the ships.
 * @author Xueqi Wang, Weijie Qi
 */
public abstract class Ship {
	/**
	 * "bowRow" is the row that contains the bow(front part of the ship).
	 */
	private int bowRow;
	/**
	 * "bowColumn" is the column that contains the bow(front part of the ship).
	 */
	private int bowColumn;
	/**
	 * "length" is the length of the ship.
	 */
	private int length;
	/**
	 * "horizontal" is a boolean that represents whether the ship is going to be placed horizontally or vertically.
	 */
	private boolean horizontal;
	/**
	 * "hit" is an array of 4 booleans that indicate whether that part of the ship has been hit or not.
	 */
	private boolean[] hit;
	
	/**
	 * The constructor initializes a ship of certain length and the hit array.
	 */
	public Ship (int length) {
		// set the length property of the particular ship
		this.length = length;
		
		// initialize the hit array
		this.hit = new boolean[4];
	}
	
	/**
	 * Get the current ship length
	 * @return ship length
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Get the row value of the current bow
	 * @return the row value of bow
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	
	/**
	 * Get the column value location of the current bow
	 * @return the column value of bow
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	/**
	 * Return the index showing which part of the ship is hit
	 * @return hit array
	 */
	public boolean[] getHit() {
		return this.hit;
	}
	
	/**
	 * Return whether the ship is horizontal or not
	 * @return ship state (horizontal or not)
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * Set the value of bowRow
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Set the value of bowColumn
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Set the value of the instance variable horizontal
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Return the type of ship as a String
	 * @return type of ship
	 */
	public abstract String getShipType();
	
	/**
	 * Return true if it's okay to put a ship
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return the boolean value whether it is OK to place the ship
	 */

	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// check the range
		if(row > 9 || row < 0)
			return false;
		else if(column > 9 || column < 0)
			return false;
		if(horizontal == true) {
			if((column-this.getLength()+1)<0)
				return false;
		}
		else {
			if((row-this.getLength()+1)<0)
				return false;
		}
		
		// compute the check range(the rectangle)
		int r0 = Math.min(row+1, 9);    // the minimum coordinate is (0,0)
		int c0 = Math.min(column+1, 9);
		int r1, c1;
		if(horizontal == true) {
			r1 = Math.max(row-1, 0);    // the maximum coordinate is (9,9)
			c1 = Math.max(column-this.getLength(), 0);
		}
		else {
			r1 = Math.max(row-this.getLength(), 0);
			c1 = Math.max(column-1, 0);
		}
		
		// check the occupancy
		for(int i=r0; i>=r1; i--) {
			for(int j=c0; j>=c1; j--) {
				if(ocean.isOccupied(i, j) == true)
					return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * Put the ship in the ocean,update the ship array at the same time
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// check the availability
		if(this.okToPlaceShipAt(row, column, horizontal, ocean) == true) {
			// store the values
			this.setBowColumn(column);
			this.setBowRow(row);
			this.setHorizontal(horizontal);
			
			// modify the ocean
			// put the ship horizontally
			if(horizontal == true) {
				for(int i=0; i<this.getLength(); i++)
					ocean.getShipArray()[row][column-i] = this;
			}
			// put the ship vertically
			else {
				for(int i=0; i<this.getLength(); i++)
					ocean.getShipArray()[row-i][column] = this;
			}
		}
		else
			System.out.println("The ship cannot be put at this place.");
	}
	
	/**
	 * This method marks the ship as hit is the shoot hits the ship
	 * @param row
	 * @param column
	 * @return the shoot is miss or not
	 */
	boolean shootAt(int row, int column) {
		// the ship has been put horizontally
		if(this.horizontal == true) {
			// check the row
			if(row == this.bowRow) {
				// check the column
				if(column <= this.bowColumn && column >= (this.bowColumn-this.getLength())) {
					this.hit[this.bowColumn - column] = true;
					return true;
				}
			}
		}
		// the ship has been put vertically
		else if(this.horizontal == false) {
			// check the column
			if(column == this.bowColumn) {
				// check the row
				if(row <= this.bowRow && row >= (this.bowRow-this.getLength())) {
					this.hit[this.bowRow-row] = true;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method returns true if every part of the ship has been hit
	 * @return true if every part of the ship has been hit
	 */
	boolean isSunk() {
		// check hit[4]
		for(int i=0; i<this.getLength(); i++) {
			// the ship has not been sunk if one part has not been hit
			if(this.hit[i] == false)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Showing whether the ship is sunk with string, return s for sunk and x for not sunk
	 */
	public String toString() {
		boolean sunk = isSunk();
		String s;
		// sunk
		if(sunk == true)
			s = "s";
		// not sunk
		else
			s = "x";
		return s;
	}
	
}
