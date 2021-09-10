package MenuItems;

import java.util.Scanner;

/**
 * This class handles basic user input functions
 * @author Josh Flory
 *
 */
public class InputController {
	private static Scanner in = null;
	
	/**
	 * Sets the scanner for this controller
	 * @param n The scanner that will be called with 
	 */
	public static void setScanner(Scanner n) {
		in = n;
	}
	
	/**
	 * Prompts the user for an integer in an inclusive range
	 * @param prompt The message the user will see when prompted for input
	 * @param min The minimum value that the input can be
	 * @param max The maximum value that the input can be
	 * @param error The message that the user sees when the input is not correct
	 * @return The integer the user entered
	 */
	public static int promptInteger(String prompt, int min, int max, String error) {
		int i;
		while(true) {
			System.out.println(prompt);
			try {
				i = Integer.parseInt(in.nextLine());
				if(i>max) {
					System.out.println("Please enter a number less than or equal to "+max);
				} else if (i<min) {
					System.out.println("Please enter a number greater than or equal to "+min);
				} else {
					return i;
				}
			} catch(Exception e) {
				System.out.println(error);
			}
		}
	}
	
	/**
	 * Prompts the user for an integer in an inclusive range
	 * @param prompt The message the user will see when prompted for input
	 * @param min The minimum value that the input can be
	 * @param max The maximum value that the input can be
	 * @return The integer the user entered
	 */
	public static int promptInteger(String prompt, int min, int max) {
		return promptInteger(prompt, min, max, "Please enter a valid integer");
	}
	
	/**
	 * Prompts the user for an integer in an inclusive range
	 * @param prompt The message the user will see when prompted for input
	 * @return The integer the user entered
	 */
	public static int promptInteger(String prompt) {
		return promptInteger(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Prompts the user for a double in an inclusive range
	 * @param prompt The message the user will see when prompted for input
	 * @param min The minimum value that the input can be
	 * @param max The maximum value that the input can be
	 * @param error The message that the user sees when the input is not correct
	 * @return The double the user entered
	 */
	public static double promptDouble(String prompt, double min, double max, String error) {
		double i;
		while(true) {
			System.out.println(prompt);
			try {
				i = Double.parseDouble(in.nextLine());
				if(i>max) {
					System.out.println("Please enter a number less than or equal to "+max);
				} else if (i<min) {
					System.out.println("Please enter a number greater than or equal to "+min);
				} else {
					return i;
				}
			} catch(Exception e) {
				System.out.println(error);
			}
		}
	}

	/**
	 * Prompts the user for a double in an inclusive range
	 * @param prompt The message the user will see when prompted for input
	 * @param min The minimum value that the input can be
	 * @param max The maximum value that the input can be
	 * @return The double the user entered
	 */
	public static double promptDouble(String prompt, int min, int max) {
		return promptDouble(prompt, min, max, "Please enter a valid double");
	}

	/**
	 * Prompts the user for a double in an inclusive range
	 * @param prompt The message the user will see when prompted for input
	 * @return The double the user entered
	 */
	public static double promptDouble(String prompt) {
		return promptDouble(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Prompts the user for a string
	 * @param prompt The message the user will see when prompted for input
	 * @return The string the user entered
	 */
	public static String promptString(String prompt) {
		System.out.println(prompt);
		return in.nextLine();
	}
	
	/**
	 * Prompts the user for Yes or No input
	 * @param prompt The message the user will see when prompted for input
	 * @param error The message that the user sees when the input is not correct
	 * @return True if the user inputs yes, False if the user inputs no
	 */
	public static boolean promptYesNo(String prompt, String error) {
		char c;
		while(true) {
			System.out.println(prompt);
			try {
				c = in.nextLine().toLowerCase().charAt(0);
				if(c=='y' || c=='n') return (c=='y');
				System.out.println(error);
			} catch(Exception e) {
				System.out.println(error);
			}
		}
	}
	
	/**
	 * Prompts the user for Yes or No input
	 * @param prompt The message the user will see when prompted for input
	 * @return True if the user inputs yes, False if the user inputs no
	 */
	public static boolean promptYesNo(String prompt) {
		return promptYesNo(prompt,"Please enter 'y' or 'n'");
	}
}