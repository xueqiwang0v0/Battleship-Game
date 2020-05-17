package battleship;

/**
 * This class describes a specific type of ship(destroyer) with length of 2.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public class Destroyer extends Ship {
	/**
	 * The constructor initializes a specific type of ship(destroyer) with length of 2.
	 */
	public Destroyer() {
		super(2);
	}
	
	/**
	 * The function is used to get the specific ship type as a String.
	 * @return "destroyer"
	 */
	public String getShipType() {
		String type = "destroyer";
		return type;
	}
	
}
