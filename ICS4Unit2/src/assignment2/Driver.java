package assignment2;

import java.io.*;
import java.util.*;

public class Driver
{

	public static void main (String[] args) throws IOException
	{
		ArrayList<Album> albums = new ArrayList<>();
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
		int mainMenuChoice, subMenuChoice, chosenAlbum;
		mainMenuChoice = displayMenu (0, stdIn);

		if (mainMenuChoice == 1) {
			subMenuChoice = displayMenu (1, stdIn);
			if(subMenuChoice == 1) { //display all albums
				listAlbums(albums);
			}
			else if(subMenuChoice == 2) {
				chosenAlbum = promptAlbum(stdIn, albums);
				if(chosenAlbum < -1) { //shouldn't happen
					System.out.println("ERROR!!!!");
				}
				else if(chosenAlbum >= 0) {
					System.out.println(albums.get(chosenAlbum));
				}
			}
			
		}

		else if (mainMenuChoice == 2) {
			//TODO prompt for album to access
			subMenuChoice = displayMenu (2, stdIn);
		}
	}


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

	//returns the index of the chosen album (NOT THE ALBUM NUMBER)
	public static int promptAlbum(BufferedReader in, ArrayList<Album> albums) {
		if(albums.size() < 0) {
			System.out.println("There are no albums to display... Please add an album to get started!");
			return -1;
		}
		else {
			System.out.println("Please choose an album from the numbers listed below:");
			for(int i = 0; i < albums.size(); i++) {
				System.out.printf("%5d", albums.get(i).getAlbumNumber());
				if((i + 1) % 5 == 0) {
					System.out.println();
				}
			}
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
			return -999; //should be impossible
		}
	}
	
	public static void listAlbums(ArrayList<Album> albums) {
		if(albums.size() < 1) {
			System.out.println("There are no albums to display... Please add an album to get started!");
		}
		else {
			System.out.printf("%15s%15s\n", "Album number", "Date created");
			for(int i = 0; i < albums.size(); i++) {
				System.out.printf("%20d%20s\n", albums.get(i).getAlbumNumber(), albums.get(i).getCreatedDate());
			}
		}
	}

	public static void addAlbum(String fileName, ArrayList<Album> albums) {
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
			int albumNumber = Integer.parseInt(fileIn.readLine());
			Album key = new Album(albumNumber);
			int position = Collections.binarySearch(albums, key);
			if(position > 0) { //already exists an album with this number
				System.out.println("There already exists an album with the number: " + albumNumber);
				System.out.println("This album will NOT be added.");
				return;
			}
			Date createdDate = new Date(fileIn.readLine());
			int maxCapacity = Integer.parseInt(fileIn.readLine());
			Album temp = new Album(albumNumber, maxCapacity, createdDate); //finish the album and add to AL at the end
			
			int cardCount = Integer.parseInt(fileIn.readLine());
			String cardName;
			int cardHP;
			String cardType;
			Date cardDate;
			int attackCount;
			String attackNameAndDescription;
			String attackDamage;
			for(int i = 0; i < cardCount; i++) {
				cardName = fileIn.readLine();
				cardHP = Integer.parseInt(fileIn.readLine());
				cardType = fileIn.readLine();
				cardDate = new Date(fileIn.readLine());
				
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("Reading error");
		}
	}

	public static void removeAlbum(int index) {

	}

	public static void removeAlbum(Date d) {

	}

	public static void showStatistics(ArrayList<Album> albums) {

	}

}

