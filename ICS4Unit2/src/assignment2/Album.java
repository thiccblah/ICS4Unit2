package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//Nathan Chan November 3, 2025
//Album class
//this is the class file for the album type, which is an object serving
//a similar purpose to a physical card album. It has static variables tracking
//information regarding all album objects as a whole, as well as individual
//lists of cards and album numbers for each instance.
//Album objects are found in the arrayList of albums in the driver file.
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

	public void addCardFromInput() throws IOException {
		//check capacity
		if(cards.size() == maxCapacity) {
			System.out.println("The maximum capacity of this deck has been reached.");
		}
		else {
			boolean validInput;
			boolean keepAdding;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please enter the name of the pokemon: ");
			String name = in.readLine().trim();
			int HP = 0;
			do {
				validInput = true;
				try {
					System.out.print("Please enter the HP of the card: ");
					HP = Integer.parseInt(in.readLine().trim());
				} catch (NumberFormatException e) {
					validInput = false;
					System.out.print("INVALID. ");
				}
			} while (!validInput);
			System.out.print("Please enter the type of the pokemon: ");
			String type = in.readLine().trim();
			//read in attacks
			ArrayList<Attack> attacks = new ArrayList<>();
			int attackCount = 0;
			String attackName;
			String attackDescription;
			String attackDamage;
			do {
				attackCount++;
				keepAdding = true;
				System.out.print("Please enter the name of attack #" + attackCount + ": ");
				attackName = in.readLine().trim();
				System.out.print("Enter this attack's description. "
						+ "\nIf there is no description, simply enter an empty String: ");
				attackDescription = in.readLine().trim();
				if(attackDescription.equals("")) {
					attackDescription = null; //keeps description consistent w/ file inputted cards w/ no description
				}
				System.out.print("How much damage does this attack do? ");
				attackDamage = in.readLine().trim();
				attacks.add(new Attack(attackName, attackDescription, attackDamage));
				do {
					validInput = true;
					System.out.println("Does this card have more attacks? (Y/N)");
					String response = in.readLine().trim();
					if(response.equals("y") || response.equals("Y")) {
						//continue
					}
					else if(response.equals("n") || response.equals("N")) {
						keepAdding = false; //end attack prompting
					}
					else { //invalid response
						validInput = false;
						System.out.print("INVALID. ");
					}
				} while (!validInput);
			} while (keepAdding);
			
			Date tempDate = null; //null to prevent error
			do {
				validInput = true;
				try {
					System.out.print("Please enter the date obtained for this card (MM/DD/YYYY): ");
					tempDate = new Date(in.readLine().trim());
				} catch (IndexOutOfBoundsException e) { //if invalid String format
					validInput = false;
					System.out.print("INVALID. ");
				}
			} while (!validInput);
			
			Card tempCard = new Card(name, HP, type, tempDate);
			tempCard.setAttacks(attacks);
			cards.add(tempCard);
			//increment static variables
			totalCards++;
			totalHP += HP; //class variable
			albumTotalHP += HP; //instance variable
			//don't close system.in
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
			totalHP += c.getHP(); //class variable
			albumTotalHP += c.getHP(); //instance variable
		}
	}
	
	public void removeCard() {
		if(cards.size() <= 0) {
			System.out.println("There are no cards in this album..."
					+ "\nPlease add a card to get started!");
			return;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//display first and last card based on current sort
		System.out.println("First listed card: ");
		System.out.println("Name: " + cards.get(0).getName());
		System.out.println("HP: " + cards.get(0).getHP());
		System.out.println("Date Obtained: " + cards.get(0).getDateObtained());
		
		System.out.println("Last listed card: ");
		int lastIndex = cards.size() - 1;
		System.out.println("Name: " + cards.get(lastIndex).getName());
		System.out.println("HP: " + cards.get(lastIndex).getHP());
		System.out.println("Date Obtained: " + cards.get(lastIndex).getDateObtained());
		
		System.out.println("Please select a mode: "
				+ "\n1) Remove by name"
				+ "\n2) Remove by HP"
				+ "\n3) First listed card"
				+ "\n4) Last listed card");
		int modeSelection = 0;
		boolean validInput;
		do {
			validInput = true;
			try {
				modeSelection = Integer.parseInt(in.readLine());
				if(modeSelection < 1 || modeSelection > 4) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				validInput = false;
				System.out.println("INVALID. Please enter an integer from 1 to 4.");
			} catch (IOException e) {
				validInput = false;
				System.out.println("reader error");
			}
		} while (!validInput);
		
		//MAKE SURE TO UPDATE STATIC VARIABLES!!!!!!
		if(modeSelection == 1) { //remove by name
			Collections.sort(cards, new SortCardByName()); //sort every time since array is changing
			System.out.println("Listed below are all the cards in this album:"); //print all cards
			for(int i = 0; i < cards.size(); i++) {
				System.out.println("Name: " + cards.get(i).getName());
				System.out.println("HP: " + cards.get(i).getHP());
				System.out.println("Date Obtained: " + cards.get(i).getDateObtained());
			}
			System.out.print("Enter the name of the card to remove: ");
			int index = 0;
			Card key = null; //to avoid initialization error
			do {
				validInput = true;
				try {
					key = new Card(in.readLine().trim(), 0, "", new Date("0/0/0"));
					index = Collections.binarySearch(cards, key, new SortCardByName());
					if(index < 0) {
						System.out.println("There is no card with that name!\n"
								+ "Please enter a name from the list above.");
						validInput = false;
					}
				} catch (IOException e) {
					validInput = false;
					System.out.println("Reading error");
				}
			} while (!validInput);
			//find the left bound of this name
			int leftBound = index;
			for(int i = index - 1; i >= 0; i--) {
				if(!key.getName().equals(cards.get(i).getName())) { //not the same, left bound is the element to the right
					leftBound = i + 1;
					break;
				}
			}
//			System.out.println(leftBound);
			while(leftBound < cards.size() && cards.get(leftBound).getName().equalsIgnoreCase(key.getName())) { //while card at left bound has same name as key
				//increment static variables
				totalCards--;
				totalHP -= cards.get(leftBound).getHP(); //class variable
				albumTotalHP -= cards.get(leftBound).getHP(); //instance variable
				
				cards.remove(leftBound); //sorted list
			}
		}
		else if(modeSelection == 2) { //remove by HP
			Collections.sort(cards, new SortCardbyHP());
			System.out.println("Listed below are all the cards in this album:");
			for(int i = 0; i < cards.size(); i++) {
				System.out.println("Name: " + cards.get(i).getName());
				System.out.println("HP: " + cards.get(i).getHP());
				System.out.println("Date Obtained: " + cards.get(i).getDateObtained());
			}
			System.out.print("Enter the HP to remove: ");
			
			int index = 0;
			Card key = null; //to avoid initialization error
			do {
				validInput = true;
				try {
					key = new Card("", Integer.parseInt(in.readLine().trim()), "", new Date("0/0/0"));
					index = Collections.binarySearch(cards, key, new SortCardbyHP());
					if(index < 0) {
						System.out.println("There is no card with that HP!\n"
								+ "Please enter an HP from the list above.");
						validInput = false;
					}
				} catch (NumberFormatException e) {
					validInput = false;
					System.out.print("Please enter a valid Integer: ");
				} catch (IOException e) {
					validInput = false;
					System.out.println("Reading error");
				}
			} while (!validInput);
			//find the left bound of this name
			int leftBound = index;
			for(int i = index - 1; i >= 0; i--) {
				if(key.getHP() != cards.get(i).getHP()) { //not the same, left bound is the element to the right
					leftBound = i + 1;
					break;
				}
			}
//			System.out.println(leftBound);
			while(leftBound < cards.size() && cards.get(leftBound).getHP() == key.getHP()) { //while card at left bound has same name as key
				//increment static variables
				totalCards--;
				totalHP -= cards.get(leftBound).getHP(); //class variable
				albumTotalHP -= cards.get(leftBound).getHP(); //instance variable
				
				cards.remove(leftBound); //sorted list
			}
		}
		else if(modeSelection == 3) { //remove first card
			//increment static variables
			totalCards--;
			totalHP -= cards.get(0).getHP(); //class variable
			albumTotalHP -= cards.get(0).getHP(); //instance variable
			
			cards.remove(0); //sorted list
		}
		else { //remove last card
			//increment static variables
			totalCards--;
			totalHP -= cards.get(lastIndex).getHP(); //class variable
			albumTotalHP -= cards.get(lastIndex).getHP(); //instance variable
			
			cards.remove(lastIndex); //sorted list
		}
	}
	
	public void editAttack() {
		int cardIndex = promptCard();
		if(cardIndex >= 0) {
			int attackIndex = cards.get(cardIndex).promptAttack();
			if(attackIndex >= 0) {
				boolean validInput;
				int modeChoice = 0;
				do {
					validInput = true;
					System.out.println("Please select a field to edit: "
							+ "\n1) Name"
							+ "\n2) Description"
							+ "\n3) Damage");
					
				} while (!validInput);
			}
		}
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

