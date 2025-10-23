package assignment2;

import java.util.ArrayList;

public class Album {
	private static int albumCount, totalCards, totalHP;
	private int albumNumber;
	private int maxCapacity;
	private ArrayList<Card> cards;
	private Date createdDate;
	
	public Album(int albumNumber, int maxCapacity, Date createdDate) {
		cards = new ArrayList<>();
		this.albumNumber = albumNumber;
		this.maxCapacity = maxCapacity;
		this.createdDate = createdDate;
		albumCount++;
	}
	
	public boolean equals(Object obj) {
		Album temp = (Album) obj; 
		//by date
		return false;
	}
	
	public void displayCards() {
		
	}
	
	public void cardInfo(int index) {
		//could remove this for a toString in card.java
	}
	
	public void addCard(Card c) {
		//check capacity
	}
	
	public void removeCard(int index) {
		//find all cards that are the same through searching left and right
	}
	
	public void editAttack(int cardIndex) {
		
	}
	
	public void sortDislay(int sortMode) {
		
	}
}

