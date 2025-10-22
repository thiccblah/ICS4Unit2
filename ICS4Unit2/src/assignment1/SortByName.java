package assignment1;

import java.util.Comparator;

//sorts by name alphabetically ascending
public class SortByName implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		return player1.getName().compareToIgnoreCase(player2.getName());
	}
}
