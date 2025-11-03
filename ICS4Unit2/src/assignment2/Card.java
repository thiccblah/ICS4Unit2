package assignment2;

import java.util.ArrayList;

//Nathan Chan November 3, 2025
//Card class
//This is the class file for the Card class.
//Card Objects are based upon pokemon cards, with each card having a name, HP, attacks
//, attack descriptions, etc.
//The card object is found in the cards arrayList of Album objects.
public class Card {
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
	
	public String toString() {
		return String.format("Name: %s%nDate Obtained: %s", name, dateObtained);
	}
	
	public int compareTo(Card c) {
		//sort by name
		return 0;
	}
}
