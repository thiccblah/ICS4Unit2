package assignment2;

import java.util.ArrayList;

public class Card {
	private String name;
	private int HP;
	private String type;
	private Date obtained;
	private ArrayList<Attack> attacks;
	
	public Card(String name, int HP, String type, Date obtained) {
		attacks = new ArrayList<>();
		this.name = name;
		this.HP = HP;
		this.type = type;
		this.obtained = obtained;
	}
	
	public String toString() {
		return null;
	}
	
	public int compareTo(Card c) {
		//sort by name
		return 0;
	}
}
