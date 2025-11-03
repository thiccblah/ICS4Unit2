package assignment2;

public class Attack {
	private String name;
	private String description;
	private String damage;
	
	public Attack(String name, String description, String damage) {
		this.name = name;
		this.description = description;
		this.damage = damage;
	}
	
	public String toString() {
		String result = "";
		result += name + "\n";
		if(description != null) {
			result += description + "\n";
		}
		try {
			if(Integer.parseInt(damage) == 0) { //if 0 damage, do not print
				//do nothing
			}
			else {
				result += damage + "\n";
			}
		} catch (NumberFormatException e) { //##x damage counters will triger, still print
			result += damage + "\n";
		}
		return result;
	}
}
