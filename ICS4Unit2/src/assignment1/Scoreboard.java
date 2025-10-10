package assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scoreboard {

	public static void main(String[] args) throws IOException {
		//create arrayList of players
		ArrayList<Player> players = new ArrayList<Player>();
		//read in the scoreboard file
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader("scoreboard.txt"));
			String data;
			while((data = fileIn.readLine()) != null) {
				players.add(new Player(data.trim()));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(0);
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
		
		if(mode == 1) { //search by name;
			
		}
		in.close();
	}

}
