package assignment1;

import java.util.Comparator;

//zero-returning version of power sorting that can be used to find the index of a power with binarySearch
//Cannot use binarySearch with SortByPowerAndAlpha b/c it does not return 0, therefore all keys are never found
public class SortByPower implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		return player1.getPower().compareToIgnoreCase(player2.getPower());
	}
}
