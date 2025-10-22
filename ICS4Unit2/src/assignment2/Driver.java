package assignment2;

import java.io.*;

public class Driver
{

    public static void main (String[] args) throws IOException
    {
	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
	int mainMenuChoice, subMenuChoice;
	mainMenuChoice = displayMenu (0, stdIn);

	if (mainMenuChoice == 1)
	    subMenuChoice = displayMenu (1, stdIn);
	else if (mainMenuChoice == 2)
	    subMenuChoice = displayMenu (2, stdIn);
    }
    
    public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException
    {
    	
    	if (menuNum == 0)
    	{
    		System.out.println ("----------  MAIN MENU  -----------");
    		System.out.println ("1) Accessing your list of albums");
    		System.out.println ("2) Accessing within a particular album");
    		System.out.println ("3) Exit");
    		System.out.println ("----------------------------------");
    	}
    	else if (menuNum == 1)
    	{
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
    	
    	int choice = Integer.parseInt (stdIn.readLine ());
    	
    	
    	return choice;
    }
}

