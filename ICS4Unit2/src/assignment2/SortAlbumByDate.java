package assignment2;

import java.util.Comparator;

//Nathan Chan November 3, 2025
//This is the comparator that sorts Album objects by created date ascending.
public class SortAlbumByDate implements Comparator<Album> {
	public int compare(Album a1, Album a2) {
		Date d1 = a1.getCreatedDate();
		Date d2 = a2.getCreatedDate();
		int index;
		index = d1.getYear() - d2.getYear();
		if(index == 0) { //same year; compare months
			index = d1.getMonth() - d2.getMonth();
		}
		if(index == 0) { //same month; compare days
			index = d1.getDay() - d2.getDay();
		}
		return index;
	}
}
