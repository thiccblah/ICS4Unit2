package assignment2;

import java.io.*;
import java.util.*;

//Nathan Chan November 3, 2025
//Assignment 4: Pokemon Cards collection!
//This file is the driver class for the pokemon collection, which handles
//the bulk of the program (prompting albums, loading menus, etc.)
public class Driver
{

	public static void main (String[] args) throws IOException
	{
		ArrayList<Album> albums = new ArrayList<>();
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
		int mainMenuChoice, subMenuChoice, chosenAlbum;
		preloadAlbums(albums); //testing
		do {
			mainMenuChoice = displayMenu (0, stdIn);
			
			if (mainMenuChoice == 1) {
				do {
					subMenuChoice = displayMenu (1, stdIn);
					if(subMenuChoice == 1) { //display all albums
						listAlbums(albums);
					}
					else if(subMenuChoice == 2) { //display one album
						chosenAlbum = promptAlbum(stdIn, albums, 1);
						if(chosenAlbum < -1) { //shouldn't happen
							System.out.println("ERROR!!!!");
						}
						else if(chosenAlbum >= 0) {
							System.out.println(albums.get(chosenAlbum));
						}
					}
					else if(subMenuChoice == 3) { //add an album
						System.out.print("Enter the name of the album file: ");
						addAlbum(appendExtension(stdIn.readLine()), albums);
					}
					else if(subMenuChoice == 4) { //remove an album
						removeAlbum(stdIn, albums);
					}
					else if(subMenuChoice == 5) { //show statistics
						showStatistics(albums);
					}
				} while (subMenuChoice != 6); //return to menu
			}
			
			else if (mainMenuChoice == 2) {
				do {
					subMenuChoice = displayMenu (2, stdIn); //prompt menu item
					if(subMenuChoice == 7) {
						break;
					}
					chosenAlbum = promptAlbum(stdIn, albums, 1); //prompt album
					if(subMenuChoice == 1) { //display all cards in a deck (last sorted order)
						albums.get(chosenAlbum).displayCards();
					}
					else if(subMenuChoice == 2) { //stats on one card
						albums.get(chosenAlbum).cardInfo();
					}
					else if(subMenuChoice == 3) { //add a card
						albums.get(chosenAlbum).addCardFromInput();
					}
				} while (subMenuChoice != 7);
			}
		} while (mainMenuChoice != 3);
		
		stdIn.close();
	}

	//This method displays a menu and prompts for input, handling invalid cases
	//int menuNum is used to define the specific menu to show, BF stdIn is the BF used for
	//user input.
	//returns an int representing the chosen menu item.
	public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException
	{
		int highestOption = 1;
		if (menuNum == 0)
		{
			highestOption = 3;
			System.out.println ("----------  MAIN MENU  -----------");
			System.out.println ("1) Accessing your list of albums");
			System.out.println ("2) Accessing within a particular album");
			System.out.println ("3) Exit");
			System.out.println ("----------------------------------");
		}
		else if (menuNum == 1)
		{
			highestOption = 6;
			System.out.println ("\n---------  SUB-MENU #1  ----------");
			System.out.println ("1) Display a list of all albums");
			System.out.println ("2) Display info on a particular album");
			System.out.println ("3) Add an album");
			System.out.println ("4) Remove an album");
			System.out.println ("5) Show statistics");
			System.out.println ("6) Return back to main menu.");
			System.out.println ("----------------------------------");
		}
		else
		{
			highestOption = 7;
			System.out.println ("\n---------  SUB-MENU #2  ----------");
			System.out.println ("1) Display all cards (in the last sorted order) ");
			System.out.println ("2) Display info on a particular card");
			System.out.println ("3) Add a card");
			System.out.println ("4) Remove a card (4 options)");
			System.out.println ("5) Edit attack");
			System.out.println ("6) Sort cards (3 options)");
			System.out.println ("7) Return back to main menu");
			System.out.println ("----------------------------------");
		}

		System.out.print ("\n\nPlease enter your choice:  ");

		int choice = 0;
		boolean validInput;
		do {
			validInput = true;
			try {
				choice = Integer.parseInt (stdIn.readLine ());
				if(choice < 1 || choice > highestOption) 
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				validInput = false;
				System.out.println("Invalid number. Please enter a number from 1 to " + highestOption + ".");
			}
		} while (!validInput);

		return choice;
	}

	//this method prompts the user for album(s) to be selected for an operation. 
	//BF in is the BF for user input, AL<Album> albums is the AL of album objects available,
	//int mode differentiates the mode (mode 1 - select by album #; mode 2 - select by date(all albums
	//w/ same date).
	//returns the index of the chosen album (NOT THE ALBUM NUMBER)
	public static int promptAlbum(BufferedReader in, ArrayList<Album> albums, int mode) {
		if(albums.size() < 1) {
			System.out.println("There are no albums to display... Please add an album to get started!");
			return -1;
		}
		if(mode == 1) { //select by album #
			System.out.println("Please choose an album from the numbers listed below:");
			for(int i = 0; i < albums.size(); i++) {
				System.out.printf("%-5d", albums.get(i).getAlbumNumber());
				if((i + 1) % 5 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			int index;
			boolean validInput;
			do {
				validInput = true;
				try {
					int inputNumber = Integer.parseInt(in.readLine());
					Album key = new Album(inputNumber);
					index = Collections.binarySearch(albums, key);
					if(index < 0)
						throw new NumberFormatException();
					else
						return index;
				} catch (NumberFormatException e) {
					validInput = false;
					System.out.println("INVALID. Please enter a number from the list shown above.");
				} catch (IOException e) {
					System.out.println("BufferedReader error");
				}
			} while (!validInput);
		}
		else { //mode == 2; select by date (selects all albums with the same date)
			int uniqueDates = 1;
			System.out.println("Please choose an album from the dates listed below:");
			Album temp = new Album(-1, -1, albums.get(0).getCreatedDate());
			System.out.println("1) " + temp.getCreatedDate());
			for(int i = 1; i < albums.size(); i++) {
				if(!temp.equals(albums.get(i))) { //unique date, print
					temp.setCreatedDate(albums.get(i).getCreatedDate());
					System.out.println(++uniqueDates + ") " + albums.get(i).getCreatedDate());
				}
			}
			boolean validInput;
			do {
				validInput = true;
				try {
					int inputNumber = Integer.parseInt(in.readLine());
					if(inputNumber < 1 || inputNumber > uniqueDates) { //out of range of number of dates
						throw new NumberFormatException();
					}
					return inputNumber - 1; //to counteract counting from 1
				} catch (NumberFormatException e) {
					validInput = false;
					System.out.println("INVALID. Please choose a date numbered in the list above.");
				} catch (IOException e) {
					System.out.println("Reading error");
				}
			} while (!validInput);
		}
		return -999; //should be impossible
	}
	
	//this method lists the numbers and created dates of all available albums
	//AL<Album> albums is the AL of available albums
	//returns nothing
	public static void listAlbums(ArrayList<Album> albums) {
		if(albums.size() < 1) {
			System.out.println("There are no albums to display... Please add an album to get started!");
		}
		else {
			System.out.printf("%-20s%-20s\n", "Album number", "Date created");
			for(int i = 0; i < albums.size(); i++) { 
				System.out.printf("%-20d%-20s\n", albums.get(i).getAlbumNumber(), albums.get(i).getCreatedDate());
			}
		}
	}

	//this method adds an album given a .txt file.
	//String fileName is the file name of the file to be read from
	//AL<Album> albums is the AL of album objects (to add the album to)
	//returns nothing
	public static void addAlbum(String fileName, ArrayList<Album> albums) {
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
			int albumNumber = Integer.parseInt(fileIn.readLine());
			Album key = new Album(albumNumber);
			int position = Collections.binarySearch(albums, key);
//			System.out.println(position);
			if(position >= 0) { //already exists an album with this number
				System.out.println("There already exists an album with the number: " + albumNumber);
				System.out.println("This album will NOT be added.");
				fileIn.close();
				return;
			}
			Date createdDate = new Date(fileIn.readLine());
			int maxCapacity = Integer.parseInt(fileIn.readLine());
			Album tempAlbum = new Album(albumNumber, maxCapacity, createdDate); //finish the album and add to AL at the end
			
			int cardCount = Integer.parseInt(fileIn.readLine());
			String cardName;
			int cardHP;
			String cardType;
			Date cardDate;
			
			int attackCount;
			String attackInfo;
			String attackDamage;
			
			for(int i = 0; i < cardCount; i++) {
				cardName = fileIn.readLine();
				cardHP = Integer.parseInt(fileIn.readLine());
				cardType = fileIn.readLine();
				cardDate = new Date(fileIn.readLine());
				Card tempCard = new Card(cardName, cardHP, cardType, cardDate);
				attackCount = Integer.parseInt(fileIn.readLine());
				for(int j = 0; j < attackCount; j++) {
					attackInfo = fileIn.readLine();
					attackDamage = fileIn.readLine();
					if(attackInfo.indexOf('-') < 0) { //no dash, so no description
						tempCard.addAttack(new Attack(attackInfo, null, attackDamage));
					}
					else {
						tempCard.addAttack(new Attack(attackInfo.substring(0, attackInfo.indexOf('-') - 1), 
								attackInfo.substring(attackInfo.indexOf('-') + 2), attackDamage));
					}
				}
				tempAlbum.addCardFromFile(tempCard);
			}
			albums.add(-(position + 1), tempAlbum); //add album into AL using insertion point
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("Reading error");
		}
//		System.out.println("complete"); //testing
	}

	//prompts user for album(s) and removes album(s) either by album # or by date created
	//BF stdIn is the BF for user input, AL<Album> albums is the AL of albums
	//returns nothing
	public static void removeAlbum(BufferedReader stdIn, ArrayList<Album> albums) {
		if(albums.size() < 1) {
			System.out.println("There are no albums to remove... Please add an album to get started!");
			return;
		}
		boolean validInput;
		int modeChoice = -1;
		do {
			System.out.println("Please select the remove mode:\n"
					+ "1) Remove by album #\n"
					+ "2) Remove by date");
			validInput = true;
			try {
				modeChoice = Integer.parseInt(stdIn.readLine());
				if(modeChoice > 2 || modeChoice < 1) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				validInput = false;
				System.out.print("INVALID. ");
			} catch (IOException e) {
				validInput = false;
				System.out.println("Reader error. ");
			}
		} while (!validInput);
		if(modeChoice == 1) { //remove by album #
			albums.remove(promptAlbum(stdIn, albums, 1));
			System.out.println("1 album removed."); //there can only be one album for each album number
		}
		else { //remove by date
			Collections.sort(albums, new SortAlbumByDate());
			int index = promptAlbum(stdIn, albums, 2);
//			System.out.println(index);
			Album key = new Album(0, 0, albums.get(index).getCreatedDate());
			int leftBound = index;
			for(int i = index - 1; i >= 0; i--) { //search left
				if(!key.equals(albums.get(i))) { //not the same, left bound is the element to the right
					leftBound = i + 1;
				}
			}
//			System.out.println(leftBound);
			//remember to check that leftBound is valid since AL gets resized
			int albumsRemoved = 0;
			while(leftBound < albums.size() && albums.get(leftBound).equals(key)) { //while album at left bound has same date as key
				System.out.println("Removed album #" + albums.get(leftBound).getAlbumNumber() + ".");
				albumsRemoved++;
				albums.remove(leftBound); //sorted list
			}
			System.out.println(albumsRemoved + " album(s) removed.");
			Collections.sort(albums); //resort albums back by album #
		}
	}

	//shows statistics of every album individually and then the statistics of the whole collection
	//AL<Album> albums is the AL of available albums
	//returns nothing
	public static void showStatistics(ArrayList<Album> albums) {
		if(albums.size() < 1) {
			System.out.println("There are no albums to show... Please add an album to get started!");
			return;
		}
		for(int i = 0; i < albums.size(); i++) {
			System.out.print(String.format("%-12s", "Album #" + albums.get(i).getAlbumNumber() + ": "));
			System.out.println(albums.get(i).getCards().size() + " out of " + albums.get(i).getMaxCapacity() + " cards");
			System.out.println(String.format("%12s%.1f", "Average HP: ", 1.0 * albums.get(i).getAlbumTotalHP() / (double) albums.get(i).getCards().size()));
		}
		System.out.print(String.format("%-12s", "ALL albums: "));
		System.out.println(Album.getTotalCards() + " out of " + Album.getTotalCapacity());
		System.out.println(String.format("%12s%.1f", "Average HP: ", Album.getTotalHP() / (double) Album.getTotalCards()));
	}

	//appends .txt to file name if not already present
	//i.e. album -> album.txt; album.txt -> album.txt
	//String data is the string to ensure the extension of
	//returns the resulting String
	public static String appendExtension(String data) {
		if(data.length() < 4)
			return data;
		if(data.substring(data.length() - 4).equals(".txt"))
			return data;
		else
			return data + ".txt";
	}
	
	//for testing, preloads the 3 provided albums
	//AL<Album> albums is the AL of albums
	//returns nothing
	public static void preloadAlbums(ArrayList<Album> albums) {
		addAlbum("Album1.txt", albums);
		addAlbum("Album2.txt", albums);
		addAlbum("Album5.txt", albums);
	}
}

