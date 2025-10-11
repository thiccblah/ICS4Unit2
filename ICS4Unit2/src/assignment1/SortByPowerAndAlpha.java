package assignment1;

import java.util.Comparator;

//DO NOT USE WITH BINARYSEARCH
//this comparator does not return 0, and is only used for sorting lists
public class SortByPowerAndAlpha implements Comparator<Player> {
	public int compare(Player player1, Player player2) {
		int index = player1.getPower().compareToIgnoreCase(player2.getPower());
		if(index == 0) {
			return player1.getName().compareToIgnoreCase(player2.getName()); //if same power, sort alphabetically
		}
		return index;
	}
}
