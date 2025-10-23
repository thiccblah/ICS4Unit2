package assignment2;

import java.io.*;
import java.util.*;

public class Driver
{

	public static void main (String[] args) throws IOException
	{
		ArrayList<Album> albums = new ArrayList<>();
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
		int mainMenuChoice, subMenuChoice;
		mainMenuChoice = displayMenu (0, stdIn);

		if (mainMenuChoice == 1)
			subMenuChoice = displayMenu (1, stdIn);
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
	
	public static void listAlbums(ArrayList<Album> albums) {
		
	}
	
	public static void albumInfo(Album a) {
		
	}
	
	public static void addAlbum(String fileName) {
		
	}
	
	public static void removeAlbum(int index) {
		
	}
	
	public static void removeAlbum(Date d) {
		
	}
	
	public static void showStatistics(ArrayList<Album> albums) {
		
	}
	
}

