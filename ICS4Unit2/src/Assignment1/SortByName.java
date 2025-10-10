package Assignment1;

import java.util.Comparator;

public class SortByName implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		return player1.getName().compareToIgnoreCase(player2.getName());
	}
}
