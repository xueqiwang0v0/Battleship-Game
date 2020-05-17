package battleship;

/**
 * This class describes a specific type of ship(cruiser) with length of 3.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public class Cruiser extends Ship{
	/**
	 * The constructor initializes a specific type of ship(cruiser) with length of 3.
	 */
	public Cruiser() {
		super(3);
	}
	
	/**
	 * The function is used to get the specific ship type as a String.
	 * @return "cruiser"
	 */
	public String getShipType() {
		String type = "cruiser";
		return type;
	}
}
