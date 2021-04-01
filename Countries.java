
/**
 * 
 */
import java.util.*;
import java.io.*;

/**
 * @author Ray Wang and Zachary Yin
 *ICS 4U0 with Ms. Krasteva
 *This program will read countries and its population in a file a sort it alphabetically and numerically
 *and will write to a new file as well
 */
public class Countries {
	private static ArrayList<String> country = new ArrayList<String>();	//array list for the country names
	private static ArrayList<Integer> population = new ArrayList<Integer>();	//population in integer form
	private static ArrayList<String> commaPop = new ArrayList<String>();	//population in string form with the commas

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//read the file and assign values to country and population
		try {
			BufferedReader s = new BufferedReader(new FileReader("Countries-Population.txt"));
			String line;
			while ((line = s.readLine()) != null) {
				String[] split = line.split(" ");
				country.add(country(split));
				population.add(population(split));
			}

		} catch (Exception e) {}
		
		//Main Menu
		int num = 0;	//for the program to know which name to name the file its going to create
		boolean valid = true;
		while (valid) {
			System.out.println(
					"Main Menu\nPress 1 to sort the file alphabetically\nPress 2 to sort the file numerically\nPress 3 to print the file\nPress 4 to write to a new file\nPress 5 to exit the program");
			String input = in.nextLine();
			String fileName = "";
			//sort alphabetically
			if(input.equals("1")) {
				alphabetical();
				num = 1;
			}
			//sort numerically
			else if(input.equals("2")) {
				numerical();
				num = 2;
			}
			//print sorted arrays
			else if(input.equals("3")) {
				commaPop.clear();
				addComma();
				printStats(country, commaPop);
			}
			//write to new file
			else if(input.equals("4")) {
				boolean validFile = false;
				if(num == 1) {
					fileName = "sortedByCountry.txt";
				}
				if(num == 2) {
					fileName = "sortedByPopulation.txt";
				}
				
				try {
					PrintWriter pw = new PrintWriter(new FileWriter (fileName));
					for(int i = 0; i<country.size(); i++) {
						commaPop.clear();
						addComma();
						pw.printf("%-34s%14s%n", country.get(i), commaPop.get(i));
					}
					pw.close();
					validFile = true;
				}catch(IOException e) {
					System.out.println("You did not sort any array yet");
				}
				
				if(validFile) {
					System.out.println("Successfully wrote data to " + fileName);
				}
			}
			//exit program
			else if(input.equals("5")){
				System.out.println("Goodbye");
				System.exit(0);
			}
			//error trap
			else {
				System.out.println("Invalid Input - Please try again");
			}
		}
	}
	
	/**
	 * 
	 * @param arr- the split array that reads each line
	 * @return- the country name
	 */
	public static String country(String[] arr) {
		//countries with 5 words
		if (arr[1].equals("Democratic") || arr[1].equals("Vincent")) {
			return (arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]);
		} 
		//countries with 4 words
		else if (arr[1].equals("Kitts") || arr[0].equals("São")) {
			return (arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]);
		} 
		//countries with 3 words
		else if (arr[0].equals("Antigua") || arr[0].equals("Bosnia") || arr[0].equals("Central")
				|| arr[0].equals("Congo") || arr[0].equals("Papua") || arr[0].equals("Trinidad")
				|| arr[2].equals("Emirates")) {
			return (arr[0] + " " + arr[1] + " " + arr[2]);
		} 
		//countries with 2 words
		else if (arr[0].equals("Burkina") || arr[0].equals("Cape") || arr[0].equals("Costa") || arr[0].equals("Côte")
				|| arr[0].equals("Czech") || arr[0].equals("Dominican") || arr[0].equals("East") || arr[0].equals("El")
				|| arr[0].equals("Equatorial") || arr[1].equals("North") || arr[1].equals("South")
				|| arr[0].equals("Marshall") || arr[0].equals("Myanmar") || arr[1].equals("Zealand")
				|| arr[1].equals("Lucia") || arr[1].equals("Marino") || arr[0].equals("Saudi")
				|| arr[0].equals("Sierra") || arr[0].equals("Solomon") || arr[1].equals("Africa")
				|| arr[0].equals("Sri") || arr[1].equals("Kingdom") || arr[1].equals("States")
				|| arr[0].equals("Vatican") || arr[1].equals("Sahara")) {
			return (arr[0] + " " + arr[1]);
		} 
		//countries with 1 word
		else {
			return arr[0];
		}
	}

	/**
	 * 
	 * @param arr- the split array that reads each line
	 * @return- the population value
	 */
	public static int population(String[] arr) {
		String numStr = arr[arr.length - 1];
		String noComma = "";
		for (int i = 0; i < numStr.length(); i++) {
			//remove the commas to make into integer
			if (numStr.charAt(i) != ',') {
				noComma += numStr.charAt(i);
			}
		}
		int num = Integer.parseInt(noComma);
		return num;
	}

	/**
	 * 
	 * @param Country- the country array
	 * @param Population- the population array
	 */
	public static void printStats(ArrayList<String> Country, ArrayList<String> Population) {
		for (int i = 0; i < Country.size(); i++) {
			// System.out.println(Country.get(i)+"\t\t\t"+Population.get(i)); <--- "\t\t\t does not work for the format"
			System.out.printf("%-34s%14s%n", Country.get(i), Population.get(i));
		}
	}
	
	/**
	 * Sorts Alphabetically
	 */
	public static void alphabetical() {
		for (int i = 1; i < country.size(); i++) {
			String s = country.get(i);
			int j = i - 1;
			// compareTo() - alphabetical order compares
			while (j >= 0 && s.compareTo(country.get(j)) < 0) {
				String y = country.get(j + 1);
				int Y = population.get(j + 1);
				country.set(j + 1, country.get(j));
				country.set(j, y);
				population.set(j + 1, population.get(j));
				population.set(j, Y);
				j--;
			}
		}
	}

	/**
	 * Sorts numerically
	 */
	public static void numerical() {
		for (int i = 1; i < country.size(); i++) {
			int S = population.get(i);
			int j = i - 1;
			// compareTo() - alphabetical order compares
			while (j >= 0 && S > population.get(j)) {
				String y = country.get(j + 1);
				int Y = population.get(j + 1);
				country.set(j + 1, country.get(j));
				country.set(j, y);
				population.set(j + 1, population.get(j));
				population.set(j, Y);
				j--;
			}
		}
	}

	/**
	 * add comma to the population for the display
	 */
	public static void addComma() {
		for (int x : population) {
			String temp = Integer.toString(x);
			int count = 1;
			String num = "";
			for (int i = temp.length() - 1; i >= 0; i--) {
				if (count > 3) {
					num = "," + num;
					count = 1;
				}
				num = temp.charAt(i) + num;
				count++;
			}

			commaPop.add(num);
		}
	}

}
