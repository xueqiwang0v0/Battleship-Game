package battleship;

/**
 * This class describes empty sea(as a type of ship) with length of 1.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public class EmptySea extends Ship {
	/**
	 * The constructor initializes empty sea(as a type of ship) with length of 1.
	 */
	public EmptySea() {
		super(1);
	}
	
	/**
	 * The method returns false to indicate that nothing was hit.
	 * @return false
	 */
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * The method returns false to indicate that you didn't sink anything.
	 * @return false
	 */
	boolean isSunk() {
		return false;
	}
	
	/**
	 * The method returns "-" to indicate empty sea after shooting.
	 * @return "-"
	 */
	public String toString() {
		String s = "-";
		return s;
	}
	
	/**
	 * The method is used to get the specific ship type as a String.
	 * @return "empty"
	 */
	public String getShipType() {
		String type = "empty";
		return type;
	}
}
