package assignment1;

import java.util.Comparator;

public class SortByPower implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		return player1.getPower().compareTo(player2.getPower());
	}
}
