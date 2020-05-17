package battleship;

/**
 * This class describes a specific type of ship(submarine) with length of 1.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public class Submarine extends Ship {
	/**
	 * The constructor initializes a specific type of ship(submarine) with length of 1.
	 */
	public Submarine() {
		super(1);
	}
	
	/**
	 * The function is used to get the specific ship type as a String.
	 * @return "submarine"
	 */
	public String getShipType() {
		String type = "submarine";
		return type;
	}
}
