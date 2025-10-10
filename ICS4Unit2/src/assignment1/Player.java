package assignment1;

public class Player implements Comparable<Player> {
	private static int playerCount = 0;
	
	private int score;
	private String name;
	private String power;
	
	//constructor
	public Player(String data) {
		this.score = Integer.parseInt(data.substring(0, data.indexOf(" ")));
		this.name = data.substring(data.indexOf(" ") + 1, data.lastIndexOf(" "));
		this.power = data.substring(data.lastIndexOf(" ") + 1);
		playerCount++; //increment playerCount when new player created
	}
	
	//player count getter
	public static int getPlayerCount() {
		return playerCount;
	}
	
	//player name getter
	public String getName() {
		return name;
	}
	
	//player power getter
	public String getPower() {
		return power;
	}
	
	public boolean equals(Object obj) {
		Player p = (Player) obj;
		return this.name.equals(p.name);
	}

	public int compareTo(Player p) { //default sort by score
		return this.score - p.score;
	}
}
