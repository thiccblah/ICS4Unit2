package assignment2;

import java.util.ArrayList;

public class Album implements Comparable<Album> {
	private static int albumCount, totalCards, totalHP;
	private int albumNumber;
	private int maxCapacity;
	private ArrayList<Card> cards;
	private Date createdDate;
	private int albumTotalHP;
	
	public Album(int albumNumber, int maxCapacity, Date createdDate) {
		cards = new ArrayList<>();
		this.albumNumber = albumNumber;
		this.maxCapacity = maxCapacity;
		this.createdDate = createdDate;
		albumCount++;
	}
	
	public Album(int albumNumber) {
		this.albumNumber = albumNumber;
	}
	
	public int getAlbumNumber() {
		return albumNumber;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public boolean equals(Object obj) {
		Album temp = (Album) obj; 
		//by date
		return false;
	}
	
	public String toString() {
		return String.format("%20s%d%n"
				+ "%20s%s%n"
				+ "%20s%d%n"
				+ "%20s%d%n"
				+ "%20s%d%n", "Album #:", albumNumber, "Date Created:", createdDate
				, "Max. Capacity", maxCapacity, "# of Cards:" + cards.size()
				, "Total HP:" + albumTotalHP);
	}
	
	//compareTo default sorts by albumNumber ascending
	public int compareTo(Album a) {
		return albumNumber - a.albumNumber;
	}
	
	public void displayCards() {
		
	}
	
	public void cardInfo(int index) {
		//could remove this for a toString in card.java
	}

	public void addCardFromInput(Card c) {
		//check capacity
		if(cards.size() == maxCapacity) {
			System.out.println("The maximum capacity of this deck has been reached.");
		}
		else { 
			//prompt info and stuff
		}
	}
	
	public void addCardFromFile(Card c) {
		//check capacity
		if(cards.size() == maxCapacity) {
			System.out.println("The maximum capacity of this deck has been reached.");
			System.out.println("The card \"" + c.getName() + "\" will not be added.");
		}
		else {
			cards.add(c);
		}
	}
	
	public void removeCard(int index) {
		//find all cards that are the same through searching left and right
	}
	
	public void editAttack(int cardIndex) {
		
	}
	
	public void sortDislay(int sortMode) {
		
	}
}

