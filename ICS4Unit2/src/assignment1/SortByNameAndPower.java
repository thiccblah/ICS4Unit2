package assignment1;

import java.util.Comparator;

//sorts by name alphabetically ascending, if names are the same case insensitive sorts by power alphabetically ascending
public class SortByNameAndPower implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		int diff = player1.getName().compareToIgnoreCase(player2.getName());
		if(diff == 0) {
			return player1.getPower().compareToIgnoreCase(player2.getPower());
		}
		return diff;
	}
}
