package assignment2;

import java.util.ArrayList;

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
	
	public String toString() {
		return String.format("Name: %s%nDate Obtained: %s", name, dateObtained);
	}
	
	public int compareTo(Card c) {
		//sort by name
		return 0;
	}
}
