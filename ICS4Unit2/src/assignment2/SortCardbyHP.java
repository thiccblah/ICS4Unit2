package assignment2;

import java.util.Comparator;

public class SortCardbyHP implements Comparator<Card> {
	public int compare(Card c1, Card c2) {
		return c1.getHP() - c2.getHP();
	}

}
