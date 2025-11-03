package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Album implements Comparable<Album> {
	private static int albumCount, totalCards, totalHP, totalCapacity;
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
		totalCapacity += maxCapacity;
	}
	
	public Album(int albumNumber) {
		this.albumNumber = albumNumber;
	}
	
	public static int getTotalCards() {
		return totalCards;
	}
	
	public static int getTotalCapacity() {
		return totalCapacity;
	}
	
	public static int getTotalHP() {
		return totalHP;
	}
	
	public int getAlbumNumber() {
		return albumNumber;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public int getAlbumTotalHP() {
		return albumTotalHP;
	}
	
	public void setCreatedDate(Date date) {
		createdDate = date;
	}
	
	public boolean equals(Object obj) { //compares by date
		Album temp = (Album) obj; 
		return createdDate.equals(temp.getCreatedDate());
	}
	
	public String toString() {
		return String.format("%-20s%d%n"
				+ "%-20s%s%n"
				+ "%-20s%d%n"
				+ "%-20s%d%n"
				+ "%-20s%d%n", "Album #:", albumNumber, "Date Created:", createdDate
				, "Max. Capacity", maxCapacity, "# of Cards:", cards.size()
				, "Total HP:", albumTotalHP);
	}
	
	//compareTo default sorts by albumNumber ascending
	public int compareTo(Album a) {
		return albumNumber - a.albumNumber;
	}
	
	public void displayCards() {
		System.out.println("This album has " + cards.size() + " cards:\n");
		for(int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i) + "\n");
		}
	}
	
	public void cardInfo() {
		int index = promptCard();
		if(index >= 0) {
			Card temp = cards.get(index);
			System.out.println("Name: " + temp.getName());
			System.out.println("HP: " + temp.getHP());
			System.out.print("Attack");
			if(cards.get(index).getAttacks().size() > 1) {
				System.out.print("s");
			}
			System.out.println(":");
			for(int i = 0; i < temp.getAttacks().size(); i++) {
				System.out.println(temp.getAttacks().get(i));
			}
			System.out.println("Date acquired:");
			System.out.println(temp.getDateObtained());
		}
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
			//increment static variables
			totalCards++;
			totalHP += c.getHP();
			albumTotalHP += c.getHP();
		}
	}
	
	public void removeCard(int index) {
		//find all cards that are the same through searching left and right
	}
	
	public void editAttack(int cardIndex) {
		
	}
	
	public void sortDislay(int sortMode) {
		
	}
	
	public int promptCard() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(cards.size() < 1) {
			System.out.println("There are no cards to display... Please add a card to get started!");
			return -1;
		}
		System.out.println("Please choose a card from the cards listed below:");
		for(int i = 0; i < cards.size(); i++) {
			System.out.println(i + 1 + ")");
			System.out.println(cards.get(i) + "\n");
		}
		int index = 0;
		boolean validInput;
		do {
			validInput = true;
			try {
				index = Integer.parseInt(in.readLine()) - 1; //match natural number with index (-1)
//				System.out.println(index);
				if(index < 0 || index >= cards.size()) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				validInput = false;
				System.out.println("INVALID. Please enter a number from the list show above.");
			} catch (IOException e) {
				validInput = false;
				System.out.println("Reading error");
			}
		} while (!validInput);
		return index;
	}
}

