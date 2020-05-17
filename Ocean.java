package battleship;

import java.util.Random;

/**
 * The class represents an ocean, including methods that place ships, shooting, etc.
 * This contains a 10*10 array of Ships, representing an "ocean", and some methods to manipulate it.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public class Ocean {
	// instance variables
	
	/**
	 * "ships" is used to quickly determine which ship is in any given location.
	 */
	private Ship[][]ships = new Ship[10][10];
	
	/**
	 * "shotsFired" is the total number of shots fired by the user.
	 */
	private int shotsFired;
	
	/**
	 * "hitCount" is the number of times a shot hit a ship.
	 */
	private int hitCount;
	
	/**
	 * "shipsSunk" is the number of ships sunk.
	 */
	private int shipsSunk;
	
	/**
	 * "map" is a String array which is used for printing.
	 */
	public String[][] map = new String[10][10];
	
	/**
	 * The constructor initializes an empty sea and initialize the counters.
	 */
	public Ocean() {
		// fill the ships array with "emptysea"
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				this.ships[i][j] = new EmptySea();
			}
		}
		
		// set the empty sea
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				this.ships[i][j].placeShipAt(i, j, true, this);
				// initialize the printed map
				map[i][j] = ". ";
			}
		}
		
		// initialize variables
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	
	// methods
	/**
	 * This method places all ten ships randomly on the ocean.
	 */
	void placeAllShipsRandomly() {
		
		Random r = new Random();
		
		int row = r.nextInt(10); // get a random number [0,10)
		int column = r.nextInt(10);
		boolean horizontal = r.nextBoolean(); // get a random boolean
		
		// place 1 battleship
		Ship battleship = new Battleship();
		// get valid coordinates and horizontal
		while(battleship.okToPlaceShipAt(row, column, horizontal, this) == false) {
			row = r.nextInt(10);
			column = r.nextInt(10);
			horizontal = r.nextBoolean();
		}
		battleship.placeShipAt(row, column, horizontal, this);
		
		// place 2 cruisers
		for(int i=0; i<2; i++) {
			row = r.nextInt(10);
			column = r.nextInt(10);
			horizontal = r.nextBoolean();
			
			Ship cruiser = new Cruiser();
			// get valid coordinates and horizontal
			while(cruiser.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = r.nextInt(10);
				column = r.nextInt(10);
				horizontal = r.nextBoolean();
			}
			cruiser.placeShipAt(row, column, horizontal, this);
		}
		
		// place 3 destroyers
		for(int i=0; i<3; i++) {
			row = r.nextInt(10);
			column = r.nextInt(10);
			horizontal = r.nextBoolean();
			
			Ship destroyer = new Destroyer();
			// get valid coordinates and horizontal
			while(destroyer.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = r.nextInt(10);
				column = r.nextInt(10);
				horizontal = r.nextBoolean();
			}
			destroyer.placeShipAt(row, column, horizontal, this);
		}
		
		
		// place 4 submarines
		for(int i=0; i<4; i++) {
			row = r.nextInt(10);
			column = r.nextInt(10);
			horizontal = r.nextBoolean();
			
			Ship submarine = new Submarine();
			// get valid coordinates and horizontal
			while(submarine.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = r.nextInt(10);
				column = r.nextInt(10);
				horizontal = r.nextBoolean();
			}
			submarine.placeShipAt(row, column, horizontal, this);
		}
	}
	
	/** 
	 * Returns true if the given location contains a ship, false if it does not 
	 * @param row 
	 * @param column 
	 * @return true if the given location contains a ship, otherwise false. 
	 */
	boolean isOccupied(int row, int column) {
		boolean occupied = false;
		String type = ships[row][column].getShipType();
		if(type == "battleship" || type == "cruiser" || type == "destroyer" || type == "submarine")
			occupied = true;
		
		return occupied;
	}
	
	/**
	 * If the part of the ship occupies the given (row, column), and not sunk, mark "hit"
	 * @param row
	 * @param column
	 * @return true/false
	 */
	boolean shootAt(int row, int column) {
		// increase shots fired
		this.shotsFired += 1;
		
		// find the corresponding ship
		Ship ship = this.getShipArray()[row][column];
		
		// check the type of the ship
		// shoot empty sea
		if(ship.getShipType() == "empty") {
			ship.shootAt(row, column);
			// update the map
			map[row][column] = ship.toString() + " ";
			System.out.println("You missed.");
			return false;
		}
		// shoot a real ship
		else {
			// the ship has not been sunk
			if(ship.isSunk() == false) {
				// increase hit count
				this.hitCount += 1;
				System.out.println("You hit a ship.");
				// shoot the ship
				ship.shootAt(row, column);
				// check whether the ship has been sunk
				if(ship.isSunk() == true) {
					this.shipsSunk += 1;
					// print the message
					System.out.println("You just sank a ship - " + ship.getShipType() + ".");
					// update the map of the rest of the ship
					if(ship.isHorizontal()==true) {
						for(int i=0; i<ship.getLength(); i++)
							map[ship.getBowRow()][ship.getBowColumn()-i] = ship.toString() + " ";
					}
					else {
						for(int i=0; i<ship.getLength(); i++)
							map[ship.getBowRow()-i][ship.getBowColumn()] = ship.toString() + " ";
					}
				}
				// update the map
				map[row][column] = ship.toString() + " ";
				return true;
			}
			// return false if the ship has been sunk
			else {
				// update the map
				map[row][column] = ship.toString() + " ";
				System.out.println("You shot the sunk ship.(miss the shot)");
				return false;
			}
		}
	}
	
	/**
	 * the method gets the the number of shots fired by the user
	 * @return the number of shots fired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * this method returns the number of times a shot hit a ship.
	 * @return returns the number of hits recorded.
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * This method gets the number of ships sunk
	 * @return the number of ships sunk
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * This method returns true if all ships have been sunk, otherwise false
	 * @return true if all ships have been sunk, otherwise false
	 */
	boolean isGameOver() {
		boolean over;
		
		// the game is over when 10 ships have been sunk
		if(this.getShipsSunk()==10)
			over = true;
		else
			over = false;
		
		return over;
	}
	
	/**
	 * This method returns the 10x10 array of Ships. 
	 * @return the 10*10 array of ships
	 */
	Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * Display the Ocean.
	 */
	void print() {
		// print the column
		System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
		
		for(int i=0; i<10; i++) {
			
			// print the row
			System.out.print(i + " ");
			
			for(int j=0; j<10; j++)
				System.out.print(map[i][j]);
			System.out.print("\n");
		}
	}
}
