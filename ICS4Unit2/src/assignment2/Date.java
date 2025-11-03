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
	
	public Date(String data) {
		month = Integer.parseInt(data.substring(0, data.indexOf('/')));
		day = Integer.parseInt(data.substring(data.indexOf('/') + 1, data.lastIndexOf('/')));
		year = Integer.parseInt(data.substring(data.lastIndexOf('/') + 1));
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
	
	public String toString() {
		return month + "/" + day + "/" + year;
	}
	
	public boolean equals(Date d) {
		return month == d.month && day == d.day && year == d.year; 
	}
}
