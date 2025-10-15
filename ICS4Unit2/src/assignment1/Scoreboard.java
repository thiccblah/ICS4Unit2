package assignment1;

import java.io.*;
import java.util.*;

public class Scoreboard {

	public static void main(String[] args) throws IOException {
		//create arrayList of players
		ArrayList<Player> players = new ArrayList<Player>();
		//read in the scoreboard file
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader("scoreboard.txt"));
			String data;
			while((data = fileIn.readLine()) != null) {
				data = data.trim();
				try {
					players.add(new Player(Integer.parseInt(data.substring(0, data.indexOf(" "))), //score
							data.substring(data.indexOf(" ") + 1, data.lastIndexOf(" ")),  //name
							data.substring(data.lastIndexOf(" ") + 1))); //power
				} catch (NumberFormatException e) { //score missing
					System.out.println("Score missing! Invalid line:\n" + data);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Invalid line:\n" + data);
				} catch (Exception e) {
					System.out.println("Unknown error! Invalid line:\n" + data);
				}
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(0);
		}
//		for(int i = 0; i < players.size(); i++) { //testing
//			System.out.println(players.get(i).getName());
//		}
		//assign rankings
		Collections.sort(players); //default sort by score
		players.get(0).setRanking(1); //highest score is always first
		for(int i = 1; i < players.size(); i++) {
			if(players.get(i).getScore() == players.get(i - 1).getScore()) { //player has the same score as the player above them, set the same rank
				players.get(i).setRanking(players.get(i- 1).getRanking()); //inherit player above's rank
			}
			else { //player at i has a different (lower) score
				players.get(i).setRanking(i + 1);
			}
		}
		Scanner in = new Scanner(System.in);
		boolean validInput;
		int mode = 0;
		do { //get valid mode
			try {
				validInput = true;
				System.out.print("Hello! Type 1 to search by name, or type 2 to search by power: ");
				mode = Integer.parseInt(in.nextLine());
				if(mode < 1 || mode > 2) {
					throw new NumberFormatException();
				}
				
			} catch (NumberFormatException e) {
				validInput = false;
				System.out.println("Invalid number. Please type in either \"1\" or \"2\".");
			}
		} while (!validInput);
		
		if(mode == 1) { //search by name
			System.out.println("Name search started");
			Collections.sort(players, new SortByName());
			boolean keepAsking;
			do {
				keepAsking = true;
				System.out.print("Please enter the name that you would like to search for (\"exit\" to stop): ");
				String name = in.nextLine().trim();
				if(name.equalsIgnoreCase("exit")) {
					keepAsking = false;
					break;
				}
				Player key = new Player(0, name, "");
//				System.out.println(key.getName());
				int index = Collections.binarySearch(players, key, new SortByName());
				if(index < 0) {
					System.out.println("There are no players with the name \"" + name + "\", Please try again.");
				}
				else {
					System.out.println("Name: " + players.get(index).getName()
							+ "\nPower: " + players.get(index).getPower()
							+ "\nScore: " + players.get(index).getScore()
							+ "\nRanking: " + players.get(index).getRanking() + " out of " + players.size());
				}
			} while (keepAsking);
		}
		else if(mode == 2) { //search by power
			System.out.println("Power search started");
			Collections.sort(players, new SortByPowerAndAlpha()); //sort alphabetically within each power so that you can simply loop print
			Comparator<Player> comparePower = new SortByPower(); //create comparator variable to compare players in this driver file
			boolean keepAsking;
			do {
				keepAsking = true;
				System.out.print("Please enter the power that you would like to search for (\"exit\" to stop): ");
				String power = in.nextLine().trim();
				if(power.equalsIgnoreCase("exit")) {
					keepAsking = false;
					break;
				}
				Player key = new Player(0, "", power);
//				System.out.println(key.getName());
				int index = Collections.binarySearch(players, key, new SortByPower());
//				for(int i = 0; i < players.size(); i++) { //testing
//					System.out.println(players.get(i));
//				}
//				System.out.println("index is" + index);
				if(index < 0) {
					System.out.println("There are no players with the power \"" + power + "\", Please try again.");
				}
				else {
					//players is already sorted by power, so all players of the same power are in consecutive lines
					//find where the section of these players start
					//I am NOT looping through the whole arrayList, just finding the bounds of the section this power takes up
					int startIndex = 0;
					for(int i = index; i >= 0; i--) { //search left
						if(comparePower.compare(key, players.get(i)) != 0) { //not the same, loop from the element after
							break;
						}
						else { //element at i has the same power, new starting point is here
							startIndex = i; //update startIndex
						}
					}
					//print until player with different power is reached
					for(int i = startIndex; i < players.size(); i++) {
						if(comparePower.compare(key, players.get(i)) != 0) { //reached the next section of power
							break;
						}
						System.out.println("Name: " + players.get(i).getName()
								+ "\nPower: " + players.get(i).getPower()
								+ "\nScore: " + players.get(i).getScore()
								+ "\nRanking: " + players.get(i).getRanking() + " out of " + players.size() + "\n");
					}
				}
			} while (keepAsking);
		}
		in.close();
		System.out.println("Thank you for using the program.");
	}

}
