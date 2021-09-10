package fsm;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Application called by BaseApp to handle various features relating to
 * photoshoot
 * 
 * @author ejshouse
 *
 */
public class PhotoshootController implements PhotoshootControllerInterface{
	private PhotoshootDate[] dates;
	private List<Photoshoot> photoshoots;

	/**
	 * initializes the photoshoot module.
	 */
	public PhotoshootController() {
		dates = generateDates();
		setPhotoshoots(new ArrayList<>());
	}

	/**
	 * Guides user through prompts, then saves a photoshoot, presuming the user
	 * entered valid responses.
	 * 
	 * @return true if it succeeds, false otherwise
	 */
	public boolean schedulePhotoshoot() {
		Scanner input = new Scanner(System.in);
		boolean validDate = false;
		for (int i = 0; i < dates.length; i++)
			if (!validDate)
				validDate = !dates[i].booked;
		if (!validDate) {
			System.out.println(
					"There does not exist a valid date to schedule a photoshoot. Please cancel a photoshoot before continuing");
			return false;
		}

		boolean dateNotValid = true;
		showDates(dates);
		String date = "n/a";
		System.out.println(
				"Please select a date from the available dates.\n" + "Please enter the date in mm/dd/yy format.");
		while (dateNotValid) {

			date = input.nextLine();
			for (int i = 0; i < dates.length; i++) {
				if (dateNotValid && !dates[i].booked) {
					dateNotValid = !(dates[i].date.equals(date));
				}
			}
			if (dateNotValid) {
				System.out.println("Please enter a valid date:");
			}
		}

		System.out.println("Please enter a fashion line.");
		String line = input.nextLine();

		System.out.println("Please enter the model's name.");
		String model = input.nextLine();

		System.out.println("Please enter the photographer's name.");
		String photographer = input.nextLine();

		System.out.println("Upload a link or file path of an image, or n/a if none exists.");
		String img = input.nextLine();

		Photoshoot photoshoot = createPhotoshoot(date, model, photographer, line, img);

		getPhotoshoots().add(photoshoot);

		for (int i = 0; i < dates.length; i++) {
			if (dates[i].date.equals(date)) {
				dates[i].booked = true;
			}
		}
		return true;
	}

	/**
	 * Cancels an existing photoshoot, unmarking it as booked. Exits if there do
	 * not exist any photoshoots or user enters invalid input.
	 * 
	 * @return true if it succeeds, false otherwise
	 */
	public boolean cancelPhotoshoot() {
		Scanner in = new Scanner(System.in);
		if (getPhotoshoots().size() == 0) {
			System.out.println("There do not exist any photoshoots to cancel.");
			return false;
		}
		showPhotoshoots();
		System.out.println("Please enter the date of the photoshoot you wish to cancel:");
		String date = in.nextLine();
		int photoshootIndex = -1;
		for (int i = 0; i < getPhotoshoots().size(); i++) {
			if (date.equals(getPhotoshoots().get(i).getDate())) {
				photoshootIndex = i;
			}
		}
		if (photoshootIndex < 0) {
			System.out.println("The following date is not valid.");
			return false;
		}
		getPhotoshoots().remove(photoshootIndex);
		for (int i = 0; i < dates.length; i++) {
			if (date.equals(dates[i].date))
				dates[i].booked = false;
		}
		System.out.println("The selected photoshoot has been deleted.");
		return true;
	}

	/**
	 * Display all currently booked photoshoots with details.
	 */
	public void showPhotoshoots() {
		for (Photoshoot p : getPhotoshoots()) {
			showPhotoshoot(p);
			System.out.println();
		}
	}

	/**
	 * prints an instance of a photoshoot
	 * 
	 * @param photoshoot
	 */
	private void showPhotoshoot(Photoshoot photo) {
		System.out.println(photo.getDate());
		System.out.println(photo.line);
		System.out.println(photo.getModel());
		System.out.println(photo.getPhotographer());
		System.out.println(photo.img);
	}

	/**
	 * Shows all valid dates for a photoshoot
	 * 
	 * @param dates
	 */
	private void showDates(PhotoshootDate[] dates) {
		System.out.println("List of all valid dates to schedule a photoshoot on: \n");
		for (int i = 0; i < dates.length; i++) {
			if (!dates[i].booked) {
				System.out.println(dates[i].date);
			}
		}
		System.out.println();
	}

	/**
	 * Generates a list of dates that can be scheduled on for a photoshoot.
	 * 
	 * @return a pre-defined list of possible photoshoot dates
	 */
	private PhotoshootDate[] generateDates() {
		PhotoshootDate[] dates = new PhotoshootDate[10];
		for (int i = 0; i < dates.length; i++) {
			String daystring;
			int day = i + 10;
			if (day < 10) {
				daystring = "0" + day;
			} else {
				daystring = "" + day;
			}
			String date = "11/" + daystring + "/19";
			dates[i] = new PhotoshootDate(date, false);
		}
		return dates;
	}

	/**
	 * creates an instance of a photoshoot with specified parameters
	 * 
	 * @param date
	 * @param model
	 * @param photographer
	 * @param line
	 * @param img
	 * @return
	 */
	private Photoshoot createPhotoshoot(String date, String model, String photographer, String line, String img) {
		return new Photoshoot(date, model, photographer, line, img);
	}

	public List<Photoshoot> getPhotoshoots() {
		return photoshoots;
	}

	public void setPhotoshoots(List<Photoshoot> photoshoots) {
		this.photoshoots = photoshoots;
	}
}
