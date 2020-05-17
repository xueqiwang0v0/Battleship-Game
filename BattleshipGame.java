package battleship;

import java.util.Scanner;

/**
 * Main class for a Human vs Computer version of Battleship game.
 * Creates a single instance of Ocean. Gets user input(row and column)
 * for interacting with and playing against the computer.
 * @author Xueqi Wang, Weijie Qi
 *
 */

public class BattleshipGame {
	/**
	 * Initialize a battleship game.
	 */
	BattleshipGame(){
		
	}
	
	/**
	 * The main method of the battleship game.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		boolean again = true;
		
		while(again == true) {
			// a new game
			new BattleshipGame().newGame(scan);
			
			// ask whether to play again
			System.out.println("Do you want to play again? y/n");
			String ans = scan.next();
			if(ans.startsWith("y") || ans.startsWith("Y"))
				again = true;
			else if(ans.startsWith("n") || ans.startsWith("N"))
				again = false;
		}
		scan.close();
	}
	/**
	  * Each time call the function, start a new battleship game.
	  * This method accepts ”shots” from the user; display the results; 
	       print final scores; and ask the user if he/she wants to play again
	  * @param scan
	  */
	void newGame(Scanner scan) {
		int row = 0;
		int column = 0;
		
		// initialize the ocean
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();
		
		boolean over = ocean.isGameOver();
		// the game continues if it's not over
		while(over == false) {
			// get the user input
			boolean valid = false;
			while(valid == false) {
				System.out.println("Enter row,column:");
				String nums[] = scan.next().split(",");
				row = Integer.parseInt(nums[0]);
				column = Integer.parseInt(nums[1]);
				if(row>=0 && row<10 && column>=0 && column<10)
					valid = true;
				else
					System.out.println("Invalid input. The number should be 0~9.");
			}
			
			// shoot at the map
			ocean.shootAt(row, column);
			
			// show the current results
			System.out.println("Sunk Ship(s): " + ocean.getShipsSunk());
			System.out.println("Shot(s) Fired: " + ocean.getShotsFired());
			System.out.println("Hit Count:" + ocean.getHitCount());
			
			// print the map
			ocean.print();
			
			// check whether the game is over
			over = ocean.isGameOver();
		}
			
			// print the final results
			System.out.println("The game is over.");
			System.out.println("You fired " + ocean.getShotsFired() + " shots.");
			
	}
}
