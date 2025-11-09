package assignment2;

import java.util.Comparator;

//Nathan Chan November 3, 2025
//This is the comparator that sorts Card objects by created date ascending (old -> new).
public class SortCardByDate implements Comparator<Card> {
	public int compare(Card c1, Card c2) {
		return c1.getDateObtained().compareTo(c2.getDateObtained());
	}

}
