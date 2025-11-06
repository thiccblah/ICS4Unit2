package assignment2;

import java.util.Comparator;

public class SortCardByName implements Comparator<Card> {
	public int compare(Card c1, Card c2) {
		return c1.getName().compareToIgnoreCase(c2.getName());
	}

}
