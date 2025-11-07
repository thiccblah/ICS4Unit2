package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Nathan Chan November 3, 2025
//Card class
//This is the class file for the Card class.
//Card Objects are based upon pokemon cards, with each card having a name, HP, attacks
//, attack descriptions, etc.
//The card object is found in the cards arrayList of Album objects.
public class Card implements Comparable<Card> {
	private String name;
	private int HP;
	private String type;
	private Date dateObtained;
	private ArrayList<Attack> attacks;
	
	public Card(String name, int HP, String type, Date dateObtained) {
		attacks = new ArrayList<>();
		this.name = name;
		this.HP = HP;
		this.type = type;
		this.dateObtained = dateObtained;
	}
	
	public int getHP() {
		return HP;
	}
	
	public void addAttack(Attack a) {
		attacks.add(a);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}
	
	public Date getDateObtained() {
		return dateObtained;
	}
	
	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}
	
	public int promptAttack() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(attacks.size() < 1) {
			System.out.println("There are no attacks to display...");
			return -1;
		}
		System.out.println("Please choose an attack from the cards listed below:");
		for(int i = 0; i < attacks.size(); i++) {
			System.out.println(i + 1 + ")");
			System.out.println(attacks.get(i) + "\n");
		}
		int index = 0;
		boolean validInput;
		do {
			validInput = true;
			try {
				index = Integer.parseInt(in.readLine()) - 1; //match natural number with index (-1)
//				System.out.println(index);
				if(index < 0 || index >= attacks.size()) {
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
	
	public String toString() {
		return String.format("Name: %s%nDate Obtained: %s", name, dateObtained);
	}
	
	public int compareTo(Card c) {
		//sort by name
		return name.compareTo(c.getName()); 
	}
}
