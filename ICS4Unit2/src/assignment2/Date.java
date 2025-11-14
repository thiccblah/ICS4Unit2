package assignment2;

//Nathan Chan November 3, 2025
//Date class
//This is the class file for the Date type, which is an object that stores
//a date three ints: month, day, and year.
//Date objects are used to store significant dates for both Album and Card objects.
public class Date {
	private int month;
	private int day;
	private int year;
	
	//Constructor
	public Date(String data) {
		month = Integer.parseInt(data.substring(0, data.indexOf('/')));
		day = Integer.parseInt(data.substring(data.indexOf('/') + 1, data.lastIndexOf('/')));
		year = Integer.parseInt(data.substring(data.lastIndexOf('/') + 1));
		
		//check for invalid numbers
		if(month < 0 || month > 12) {
			throw new NumberFormatException();
		}
		if(day < 0 || day > 31) {
			throw new NumberFormatException();
		}
		if(year < 1 || year > 2025) {
			throw new NumberFormatException();
		}
	}

	//getters
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public String toString() { //returns date in MM/DD/YYYY format
		return month + "/" + day + "/" + year;
	}
	
	public boolean equals(Date d) {
		return month == d.month && day == d.day && year == d.year; 
	}
	
	public int compareTo(Date d) { //sorts by date ascending (old -> new)
		int index = year - d.year;
		if(index == 0) { //same year
			index = month - d.month;
			if(index == 0) { //same month
				index = day - d.day;
			}
		}
		return index;
	}
}
