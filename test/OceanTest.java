package battleship;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
/**
 * This is the JUnit test class for Ocean.
 * @author Xueqi Wang, Weijie Qi
 *
 */

class OceanTest {

	Ocean ocean;
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		// test case 1
		assertTrue(ocean.isOccupied(1, 5));
		//TODO
		//More tests
		// test case 2
		assertTrue(ocean.isOccupied(0, 0));
		
		Ship cruiser = new Cruiser();
		row = 2;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);		
		// test case 3
		assertFalse(ocean.isOccupied(2, 6));
		
	}

	@Test
	void testShootAt() {
		// test case 1: no ship
		assertFalse(ocean.shootAt(0, 1));
		// test case 2: shoot different part of a ship
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests
		assertTrue(destroyer.isSunk());
		// test case 3: shoot the same part of a ship(not sunk)
		Ship cruiser = new Cruiser();
		row = 4;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(4, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(4, 9));
		
		// test case 4: shoot after a ship has been sunk
		Ship submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(submarine.isSunk());
		assertFalse(ocean.shootAt(5, 5));
		
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		assertFalse(ocean.shootAt(0,1));
		assertFalse(submarine.isSunk());
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(8, ocean.getShotsFired());
		
		Ship cruiser = new Cruiser();
		row = 2;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(7,8));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(2, 3));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(2, 2));
		assertFalse(cruiser.isSunk());
		assertEquals(11, ocean.getShotsFired());
		assertTrue(ocean.shootAt(2, 3));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(2, 2));
		assertFalse(cruiser.isSunk());
		assertEquals(13, ocean.getShotsFired());
		assertTrue(ocean.shootAt(2, 1));
		assertTrue(cruiser.isSunk());
		assertEquals(14, ocean.getShotsFired());		
		assertFalse(ocean.shootAt(2, 1));
		assertEquals(15, ocean.getShotsFired());	
	}

	@Test
	void testGetHitCount() {
		// test case 1: normal case
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		// test case 2: shoot the same part of a ship(not sunk)
		assertTrue(ocean.shootAt(1, 5));
		assertEquals(2, ocean.getHitCount());
		
		// test case 3: shoot the same part of a ship(sunk)
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertFalse(ocean.shootAt(0, 5));
		assertEquals(3, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		// test case 1: normal case
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		// test case 2: normal case
		Ship submarine = new Submarine();
		row = 2;
		column = 2;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(8, 2));
		assertFalse(submarine.isSunk());
		assertTrue(ocean.shootAt(2, 2));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// test case 3: normal case
		Ship cruiser = new Cruiser();
		row = 7;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(8, 2));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(7, 2));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(7, 3));
		assertFalse(cruiser.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		// test case 1: empty sea
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		// test case 2: place a destroyer
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals("destroyer", shipArray[1][5].getShipType());	
		assertEquals("destroyer", shipArray[0][5].getShipType());
		// test case 3: place a submarine
		Ship submarine = new Submarine();
		row = 4;
		column = 5;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals("submarine", shipArray[4][5].getShipType());
			
		
	}

}
