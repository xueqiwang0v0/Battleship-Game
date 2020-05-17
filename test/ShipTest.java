package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * This is the JUnit test class for Ship.
 * @author Xueqi Wang, Weijie Qi
 *
 */
class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		// test case 1: battleship
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		// test case 2: cruiser
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		// test case 3: destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		// test case 4: submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		// test case 5: empty sea
		ship = new EmptySea();
		assertEquals(1, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		// test case 1: normal case
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test case 2: normal case
		ocean = new Ocean();
		Ship cruiser = new Cruiser();
		row = 0;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		// test case 3: normal case
		Ship emptysea = new EmptySea();
		row = 9;
		column = 9;
		horizontal = false;
		emptysea.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, emptysea.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		// test case 1: normal case
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		// test case 2: normal case
		ocean = new Ocean();
		Ship cruiser = new Cruiser();
		row = 0;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		// test case 3: normal case
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		// test case 1: no shot, no hit
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		// test case 2: 1 hit
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);
		hits[0] = true;
		assertArrayEquals(hits, ship.getHit());
		assertTrue(ship.getHit()[0]);
		// test case 3: 1 shot, miss the hit
		ocean = new Ocean();
		ship = new Submarine();
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(0, 0);
		hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
	}

	@Test
	void testIsHorizontal​() {
		// test case 1: horizontal
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		// test case 2: horizontal
		ocean = new Ocean();
		Ship cruiser = new Cruiser();
		row = 0;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(cruiser.isHorizontal());
		// test case 3: vertical
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testGetShipType() {
		// test case 1: battleship
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		// test case 2: cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		// test case 3: destroyer
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		// test case 4: submarine
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
	}

	@Test
	void testSetBowRow() {
		// test case 1: normal case
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test case 2: normal case
		ocean = new Ocean();
		Ship cruiser = new Cruiser();
		row = 0;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		// test case 3: normal case
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		// test case 1: normal case
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		// test case 2: normal case
		ocean = new Ocean();
		Ship cruiser = new Cruiser();
		row = 0;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		// test case 3: normal case
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		// test case 1: set horizontal
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		// test case 2: set horizontal
		ocean = new Ocean();
		Ship cruiser = new Cruiser();
		row = 0;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(cruiser.isHorizontal());
		// test case 3: set vertical
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		// test case 1: test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More test
		// test case 2: test when other ships are not in the ocean
		Ship cruiser = new Cruiser();
		row = 0;
		column = 2;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok);
		// test case 3: test when other ships are not in the ocean
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok);
		
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);
		// test case 1: vertically adjacent
		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		// test case 2: the range intersects with exist ship
		Battleship battleship3 = new Battleship();
		row = 0;
		column = 3;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship which intersects with others.");
		// test case 3: horizontally adjacent
		Battleship battleship4 = new Battleship();
		row = 4;
		column = 5;
		horizontal = false;
		boolean ok4 = battleship4.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship horizontally adjacent.");
	}

	@Test
	void testPlaceShipAt() {
		// test case 1: place a battleship
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		// test case 2: place a cruiser
		Ship cruiser = new Cruiser();
		row = 3;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[3][5].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[3][2]);
		
		// test case 3: place a submarine
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertTrue(submarine.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[8][9].getShipType());
		assertEquals(submarine, ocean.getShipArray()[9][9]);
	}

	@Test
	void testShootAt() {
		// test case 1: miss
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		// test case 2: 1 hit
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		// test case 3: 2 hits
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true,false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
	}
	
	@Test
	void testIsSunk() {
		// test case 1: not sunk
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		// test case 2: sunk
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		// test case 3: sunk after shooting
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.isSunk());
		
		assertTrue(destroyer.shootAt(row, column));
		assertFalse(destroyer.isSunk());
		
		assertTrue(destroyer.shootAt(row, column-1));
		assertTrue(destroyer.isSunk());
	}

	@Test
	void testToString() {
		// test case 1: "x"
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		// test case 2: get hit, finally sunk
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals("x", submarine.toString());
		submarine.shootAt(row, column);
		assertEquals("s", submarine.toString());
		// test case 3: get hit but not sunk
		Ship destroyer = new Destroyer();
		row = 5;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals("x", destroyer.toString());
		destroyer.shootAt(row, column);
		assertEquals("x", destroyer.toString());
		
	}

}
