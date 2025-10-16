package assignment1;

import java.io.*;
import java.util.*;

public class ScoreboardBonus {

	public static void main(String[] args) throws IOException {
		//create arrayList of players
		ArrayList<Player> players = new ArrayList<Player>();
		//read in the scoreboard file
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader("scoreboardBonus.txt"));
			String data;
			while((data = fileIn.readLine()) != null) {
				data = data.trim();
				try {
					players.add(new Player(Integer.parseInt(data.substring(0, data.indexOf(" "))), //score
							data.substring(data.indexOf(" ") + 1, data.lastIndexOf(" ")),  //name
							data.substring(data.lastIndexOf(" ") + 1))); //power
				} catch (NumberFormatException e) { //score missing
					System.out.println("Score missing! Invalid line:\n" + data);
				} catch (IndexOutOfBoundsException e) { //misformatted data
					System.out.println("Invalid line:\n" + data);
				} catch (Exception e) { //technically shouldn't happen, but just in case I want to be able to see what triggered it
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
			Comparator<Player> compareName = new SortByName(); //create comparator variable to compare players in this driver file
			boolean keepAsking;
			do {
				keepAsking = true;
				System.out.print("Please enter the name that you would like to search for (\"exit\" to stop): ");
				String name = in.nextLine().trim();
				if(name.equalsIgnoreCase("exit")) {
					keepAsking = false;
					break;
				}
				Player key = new Player(name, ""); //alternate constructor
//				System.out.println(key.getName());
				int index = Collections.binarySearch(players, key, new SortByName());
				if(index < 0) {
					System.out.println("There are no players with the name \"" + name + "\", Please try again.");
				}
				else { //player has been found
					//find number of players with the same name
					int startIndex = 0;
					int endIndex = 0;
					int refRank = players.get(index).getRanking();
					String refPower = players.get(index).getPower();
					boolean allSameRank = true, allSamePower = true;
					for(int i = index; i >= 0; i--) { //search left
						if(compareName.compare(key, players.get(i)) != 0) { //not the same, loop from the element after
							break;
						}
						else { //element at i has the same name, new starting point is here
							//check if power and rank are same
							if(allSameRank) {
								if(players.get(i).getRanking() != refRank) //there are different ranks within this name
									allSameRank = false;
							}
							if(allSamePower) {
								if(!players.get(i).getPower().equalsIgnoreCase(refPower)) //there are different powers within this name
									allSamePower = false;
							}
							
							startIndex = i; //update startIndex
						}
					}
					for(int i = index + 1; i < players.size(); i++) { //search right
						if(compareName.compare(key, players.get(i)) != 0) { //not the same, last player with the same name is the element before
							endIndex = i - 1;
							break;
						}
						else { //element at i has the same name, new starting point is here
							//check if power and rank are same
							if(allSameRank) {
								if(players.get(i).getRanking() != refRank) //there are different ranks within this name
									allSameRank = false;
							}
							if(allSamePower) {
								if(!players.get(i).getPower().equalsIgnoreCase(refPower)) //there are different powers within this name
									allSamePower = false;
							}
							
							endIndex = i; //update endIndex
						}
					}
					//if only one result, just print it
					if(startIndex == endIndex)
						System.out.println(players.get(index));
					//more than one result but either power or rank is common across all instances
					else if(allSamePower || allSameRank) { //don't need to sort further, just print all of them
						if(allSamePower && allSameRank) { //all players with the same name have the same power and rank
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}
						}
						else if(allSamePower) { //sort by rank
							Collections.sort(players, new SortByNameAndRanking());
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}
						}
						else { //allSameRank == true, sort by power
							Collections.sort(players, new SortByNameAndPower()); //start and end index are preserved, but this makes sure they are properly ordered
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}							
						}
					}
					else { //more than one result
						System.out.print("There is more than one player with the name \"" + name + "\".\n"
								+ "Please type \"power\" to sort by power or \"ranking\" to sort by ranking: ");
						String mode2;
						do { //get valid mode selection
							validInput = true;
							mode2 = in.nextLine().trim();
							if(!mode2.equalsIgnoreCase("power") && !mode2.equalsIgnoreCase("ranking")) {
								validInput = false;
								System.out.print("Invalid input. Please type either \"power\" or \"ranking\": ");
							}
						} while (!validInput);
						
						if(mode2.equalsIgnoreCase("power")) { //sort by power
							Collections.sort(players, new SortByNameAndPower()); //start and end index are preserved, but this makes sure they are properly ordered
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}	
						}
						else { //sort by ranking
							Collections.sort(players, new SortByNameAndRanking());
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}
						}
					}
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
				Player key = new Player("", power); //alternate constructor
//				System.out.println(key.getName());
				int index = Collections.binarySearch(players, key, new SortByPower());
//				for(int i = 0; i < players.size(); i++) { //testing
//					System.out.println(players.get(i));
//				}
//				System.out.println("index is" + index);
				if(index < 0) {
					System.out.println("There are no players with the power \"" + power + "\", Please try again.");
				}
				else { //player has been found
					//players is already sorted by power, so all players of the same power are in consecutive lines
					//find where the section of these players start
					//I am NOT looping through the whole arrayList, just finding the bounds of the section this power takes up
					int startIndex = 0;
					int endIndex = 0;
					int refRank = players.get(index).getRanking();
					String refName = players.get(index).getName();
					boolean allSameRank = true, allSameName = true;
					for(int i = index; i >= 0; i--) { //search left
						if(comparePower.compare(key, players.get(i)) != 0) { //not the same, loop from the element after
							break;
						}
						else { //element at i has the same power, new starting point is here
							//check if power and rank are same
							if(allSameRank) {
								if(players.get(i).getRanking() != refRank) //there are different ranks within this power
									allSameRank = false;
							}
							if(allSameName) {
								if(!players.get(i).getName().equalsIgnoreCase(refName)) //there are different names within this power
									allSameName = false;
							}
							
							startIndex = i; //update startIndex
						}
					}
					for(int i = index + 1; i < players.size(); i++) { //search right
						if(comparePower.compare(key, players.get(i)) != 0) { //not the same, last player with the same name is the element before
							endIndex = i - 1;
							break;
						}
						else { //element at i has the same name, new starting point is here
							//check if power and rank are same
							if(allSameRank) {
								if(players.get(i).getRanking() != refRank) //there are different ranks within this name
									allSameRank = false;
							}
							if(allSameName) {
								if(!players.get(i).getName().equalsIgnoreCase(refName)) //there are different powers within this name
									allSameName = false;
							}
							
							endIndex = i; //update endIndex
						}
					}
					//if only one result, just print it
					if(startIndex == endIndex)
						System.out.println(players.get(index));
					//more than one result but either name or rank is common across all instances
					else if(allSameName || allSameRank) { //don't need to sort further, just print all of them
						if(allSameName && allSameRank) { //all players with the same name have the same power and rank
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}
						}
						else if(allSameName) { //sort by rank
							Collections.sort(players, new SortByPowerAndRanking());
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}
						}
						else { //allSameRank == true, sort by name
							Collections.sort(players, new SortByPowerAndName()); //start and end index are preserved, but this makes sure they are properly ordered
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}							
						}
					}
					else { //more than one result
						System.out.print("There is more than one player with the power \"" + power + "\".\n"
								+ "Please type \"name\" to sort by power or \"ranking\" to sort by ranking: ");
						String mode2;
						do { //get valid mode selection
							validInput = true;
							mode2 = in.nextLine().trim();
							if(!mode2.equalsIgnoreCase("name") && !mode2.equalsIgnoreCase("ranking")) {
								validInput = false;
								System.out.print("Invalid input. Please type either \"name\" or \"ranking\": ");
							}
						} while (!validInput);
						
						if(mode2.equalsIgnoreCase("name")) { //sort by power
							Collections.sort(players, new SortByPowerAndName()); //start and end index are preserved, but this makes sure they are properly ordered
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}	
						}
						else { //sort by ranking
							Collections.sort(players, new SortByPowerAndRanking());
							for(int i = startIndex; i <= endIndex; i++) {
								System.out.println(players.get(i));
							}
						}
					}
				}
			} while (keepAsking);
		}
		in.close();
		System.out.println("Thank you for using the program.");
	}

}
