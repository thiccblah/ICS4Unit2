package assignment1;

import java.util.Comparator;

public class SortByNameAndRanking implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		int diff = player1.getName().compareToIgnoreCase(player2.getName());
		if(diff == 0) {
			return player1.getRanking() - (player2.getRanking());
		}
		return diff;
	}
}
