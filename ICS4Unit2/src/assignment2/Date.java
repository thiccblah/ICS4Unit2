package assignment2;

public class Date {
	private int month;
	private int day;
	private int year;
	
	public Date(String data) {
		month = Integer.parseInt(data.substring(0, data.indexOf('/')));
		day = Integer.parseInt(data.substring(data.indexOf('/') + 1, data.lastIndexOf('/')));
		year = Integer.parseInt(data.substring(data.lastIndexOf('/') + 1));
	}
	
	public String toString() {
		return month + "/" + day + "/" + year;
	}
}
