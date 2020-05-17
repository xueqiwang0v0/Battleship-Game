package battleship;

/**
 * This class describes a specific type of ship(battleship) with length of 4.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public class Battleship extends Ship{
	/**
	 * The constructor initializes a specific type of ship(battleship) with length of 4.
	 */
	public Battleship() {
		super(4);
	}
	
	/**
	 * The function is used to get the specific ship type as a String.
	 * @return "battleship"
	 */
	public String getShipType() {
		String type = "battleship";
		return type;
	}
}
