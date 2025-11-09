package assignment2;

//Nathan Chan November 3, 2025
//Attack Class
//this is the class file for the Attack object. Each attack has a name and damage.
//It may or may not have a description.
//Attack objects can be found in the attacks arrayList of any Card.
public class Attack {
	private String name;
	private String description;
	private String damage;
	
	public Attack(String name, String description, String damage) {
		this.name = name;
		this.description = description;
		this.damage = damage;
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDamage() {
		return damage;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDamage(String damage) {
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
