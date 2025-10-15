package assignment1;

public class Player implements Comparable<Player> {
	
	private int score;
	private String name;
	private String power;
	private int ranking;
	
	//constructor
	public Player(int score, String name, String power) {
		this.score = score;
		this.name = name;
		this.power = power;
	}
		
	//player name getter
	public String getName() {
		return name;
	}
	
	//player power getter
	public String getPower() {
		return power;
	}
	
	//player score getter
	public int getScore() {
		return score;
	}
	
	//player ranking getter
	public int getRanking() {
		return ranking;
	}
	
	//player ranking setter
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public boolean equals(Object obj) {
		Player p = (Player) obj;
		return this.name.equals(p.name);
	}

	public int compareTo(Player p) { //default sort by score descending
		int diff = -(this.score - p.score);
		if(diff == 0) {
			return this.name.compareToIgnoreCase(p.name);
		}
		return diff;
	}
	
}
