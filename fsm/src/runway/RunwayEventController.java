package runway;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fsm.*;

/**
 * manages a runway event
 * 
 * @author Erik
 *
 */
public class RunwayEventController implements RunwayEventControllerInterface {

	private RunwayEventDate[] dates;
	private List<RunwayEvent> runwayEvents;

	public RunwayEventController() {
		dates = generateDates();
		setRunwayEvents(new ArrayList<>());
	}

	public List<RunwayEvent> getEvents() {
		return getRunwayEvents();
	}

	public void selectOutfits(Scanner s, RunwayEvent event) {
		ArrayList<RunwayEventTeamMember> models = event.getBusinessTeam().getModels();
		ArrayList<Outfit> outfits = new ArrayList<Outfit>();
		for (int i = 0; i < models.size(); i++) {
			outfits.add(new Outfit(models.get(i)));
		}
		ArrayList<fsm.Item> validItems = DatabaseSupport.getItemsByLine(event.getLine());

		int choice = 0;
		for (int i = 0; i < models.size(); i++) {
			while (true) {

				ArrayList<fsm.Item> selectedItems = new ArrayList<fsm.Item>();

				System.out.println("Select an item for " + models.get(i).getName() + "'s outfit from "
						+ event.getLine().getName());
				System.out.println("or enter 0 to finalize outfit");

				for (int j = 0; j < validItems.size(); j++) {
					System.out.println(
							(j + 1) + " -- " + validItems.get(j).getName() + " :: " + validItems.get(j).getType());
				}

				boolean succ = false;
				while (!succ) {
					try {
						choice = Integer.parseInt(s.nextLine());
						if (choice >= 0 && choice <= validItems.size()) {
							succ = true;
						} else {
							System.out.println("Please enter a valid choice");
						}
					} catch (Exception e) {
						System.out.println("Please enter an integer choice");
					}
				}

				if (choice != 0) {
					selectedItems.add(validItems.get(choice - 1));
				} else {
					for (int j = 0; j < selectedItems.size(); j++) {
						outfits.get(i).addItem(selectedItems.get(j));
					}
					break;
				}
			}

		}
		
		event.setOutfits(outfits);

	}
	
	
	public void printOutfits(RunwayEvent e){
		for(Outfit o: e.getOutfits()){
			System.out.println(o);
		}
	}

	/**
	 * schedules a runway event
	 */
	public boolean scheduleRunwayEvent() {
		Scanner input = new Scanner(System.in);
		boolean validDate = false;
		for (int i = 0; i < dates.length; i++)
			if (!validDate)
				validDate = !dates[i].booked;
		if (!validDate) {
			System.out.println(
					"There does not exist a valid date to schedule a runway event. Please cancel a runway event before continuing\n");
			return false;
		}
		if (DatabaseSupport.getLines().size() == 0) {
			System.out.println(
					"There do not exist any fashion lines with which to schedule a runway event for. Please create a fashion line first\n");
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

		System.out.println();
		ArrayList<FashionLine> lines = DatabaseSupport.getLines();
		for (FashionLine line : lines) {
			System.out.println(line.toString());
		}
		System.out.println("\nPlease enter the name of a fashion line from the list of above fashion lines\n");
		String line;
		boolean lineNotValid = true;
		int lineIndex = 0;
		int lineIndexSaved = 0;
		while (lineNotValid) {
			line = input.nextLine();
			for (lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
				if (lineNotValid && (line.equals(lines.get(lineIndex).getName()))) {
					lineNotValid = false;
					lineIndexSaved = lineIndex;
				}
			}
			if (lineNotValid) {
				System.out.println("Please enter a valid fashion line: ");
			}
		}
		FashionLine lineForEvent = lines.get(lineIndexSaved);

		System.out.println("Please enter the name of the venue.");
		String venue = input.nextLine();

		System.out.println("Please enter the duration of this event (in hours rounded to nearest whole number)");
		int duration = input.nextInt();
		input.nextLine();

		System.out.println("Please enter the name of the catering company for the event.");
		String cateringCompany = input.nextLine();

		System.out.println("Please enter the names of unpaid attendees for the event."
				+ "\nPlace each attendee on its own line. " + " Type -n when you are completed.");
		ArrayList<String> unpaidInvitees = new ArrayList<>();
		boolean unpaidInviteeNotValid = true;
		while (unpaidInviteeNotValid) {
			String invitee = input.nextLine();
			if (invitee.equals("-n")) {
				unpaidInviteeNotValid = false;
			} else {
				unpaidInvitees.add(invitee);
			}
		}

		System.out.println("Please enter the ticket price. Enter it as a decimal value.");
		double ticketPrice = input.nextDouble();

		RunwayEvent runwayEvent = new RunwayEvent(date, lineForEvent, duration, venue, cateringCompany, unpaidInvitees,
				ticketPrice);

		getRunwayEvents().add(runwayEvent);

		for (int i = 0; i < dates.length; i++) {
			if (dates[i].date.equals(date)) {
				dates[i].booked = true;
			}
		}
		return true;
	}

	/**
	 * creates a business team for a given runway event.
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean createBusinessTeam() {
		Scanner in = new Scanner(System.in);
		if (getRunwayEvents().size() == 0) {
			System.out.println(
					"There do not exist any runway events for you to create a team for.\nPlease schedule a runway event first");
			return false;
		}

		System.out.println();
		showRunwayEvents();
		System.out.println(
				"Please enter the date for the runway event with which you would like to create a business team for:");
		String date = in.nextLine();
		int index = -1;
		for (int i = 0; i < getRunwayEvents().size(); i++) {
			if (date.equals(getRunwayEvents().get(i).date)) {
				index = i;
			}
		}
		if (index < 0) {
			System.out.println("The following date is not valid.");
			return false;
		}

		RunwayEvent event = getRunwayEvents().get(index);

		if (!(event.businessTeam == null)) {
			System.out.println("That runway event already has a business team assigned to it.");
			return false;
		}

		String currInput = null;

		ArrayList<RunwayEventTeamMember> models = new ArrayList<RunwayEventTeamMember>();
		ArrayList<RunwayEventTeamMember> supportingStaff = new ArrayList<RunwayEventTeamMember>();
		ArrayList<RunwayEventTeamMember> managers = new ArrayList<RunwayEventTeamMember>();
		ArrayList<RunwayEventTeamMember> photographers = new ArrayList<RunwayEventTeamMember>();
		ArrayList<RunwayEventTeamMember> designers = new ArrayList<RunwayEventTeamMember>();
		ArrayList<RunwayEventTeamMember> influencers = new ArrayList<RunwayEventTeamMember>();

		while (currInput == null || !currInput.equals("-n")) {
			RunwayEventTeamMember model = new RunwayEventTeamMember();
			System.out.println(
					"please enter a participating model's name, or type -n to move to the next member type of the business team.");
			model.name = in.nextLine();
			if (model.name.equals("-n"))
				break;
			System.out.println("please enter this model's salary. Enter this value as a decimal"
					+ " representing dollars paid during the duration of the runway event.");
			model.salary = in.nextDouble();
			in.nextLine();
			models.add(model);
			System.out.println();
		}

		while (currInput == null || !currInput.equals("-n")) {
			RunwayEventTeamMember manager = new RunwayEventTeamMember();
			System.out.println(
					"please enter a manager's name, or type -n to move to the next member type of the business team.");
			manager.name = in.nextLine();
			if (manager.name.equals("-n"))
				break;
			System.out.println("please enter this manager's salary. Enter this value as a decimal"
					+ " representing dollars paid during the duration of the runway event.");
			manager.salary = in.nextDouble();
			in.nextLine();
			managers.add(manager);
			System.out.println();
		}

		while (currInput == null || !currInput.equals("-n")) {
			RunwayEventTeamMember supportingStaffPerson = new RunwayEventTeamMember();
			System.out.println(
					"please enter a member of the supporting staff's name, or type -n to move to the next member type of the business team.");
			supportingStaffPerson.name = in.nextLine();
			if (supportingStaffPerson.name.equals("-n"))
				break;
			System.out.println("please enter this staff member's salary. Enter this value as a decimal"
					+ " representing dollars paid during the duration of the runway event.");
			supportingStaffPerson.salary = in.nextDouble();
			in.nextLine();
			supportingStaff.add(supportingStaffPerson);
			System.out.println();
		}

		while (currInput == null || !currInput.equals("-n")) {
			RunwayEventTeamMember photographer = new RunwayEventTeamMember();
			System.out.println(
					"please enter a photographer's name, or type -n to move to the next member type of the business team.");
			photographer.name = in.nextLine();
			if (photographer.name.equals("-n"))
				break;
			System.out.println("please enter this photographer's salary. Enter this value as a decimal"
					+ " representing dollars paid during the duration of the runway event.");
			photographer.salary = in.nextDouble();
			in.nextLine();
			photographers.add(photographer);
			System.out.println();
		}

		while (currInput == null || !currInput.equals("-n")) {
			RunwayEventTeamMember designer = new RunwayEventTeamMember();
			System.out.println(
					"please enter a designer's name, or type -n to move to the next member type of the business team.");
			designer.name = in.nextLine();
			if (designer.name.equals("-n"))
				break;
			System.out.println("please enter this designer's salary. Enter this value as a decimal"
					+ " representing dollars paid during the duration of the runway event.");
			designer.salary = in.nextDouble();
			in.nextLine();
			designers.add(designer);
			System.out.println();
		}

		while (currInput == null || !currInput.equals("-n")) {
			RunwayEventTeamMember influencer = new RunwayEventTeamMember();
			System.out.println(
					"please enter an influencer's name, or type -n to move to the next member type of the business team.");
			influencer.name = in.nextLine();
			if (influencer.name.equals("-n"))
				break;
			System.out.println("please enter this influencer's salary. Enter this value as a decimal"
					+ " representing dollars paid during the duration of the runway event.");
			influencer.salary = in.nextDouble();
			in.nextLine();
			influencers.add(influencer);
			System.out.println();
		}

		RunwayEventBusinessTeam businessTeam = new RunwayEventBusinessTeam(models, supportingStaff, managers,
				photographers, designers, influencers);
		event.businessTeam = businessTeam;

		System.out.println("The following business team for the runway event on " + event.date + " has been created");
		showBusinessTeamForEvent(event);
		return true;
	}

	/**
	 * shows a business team for a given runway event, if one exists
	 * 
	 * @return true if the business team gets shown, false otherwise
	 */
	public boolean showBusinessTeamForEventDialog() {
		Scanner in = new Scanner(System.in);
		if (getRunwayEvents().size() == 0) {
			System.out.println(
					"There do not exist any runway events for you to view a team for.\nPlease schedule a runway event first");
			return false;
		}

		System.out.println();
		showRunwayEvents();
		System.out.println(
				"Please enter the date for the runway event with which you would like to view its business team");
		String date = in.nextLine();
		int index = -1;
		for (int i = 0; i < getRunwayEvents().size(); i++) {
			if (date.equals(getRunwayEvents().get(i).date)) {
				index = i;
			}
		}
		if (index < 0) {
			System.out.println("The following date is not valid.");
			return false;
		}

		RunwayEvent event = getRunwayEvents().get(index);

		if (event.businessTeam == null) {
			System.out.println(
					"That runway event does not have a business team assigned to it.\nPlease create a business team first.");
			return false;
		}

		showBusinessTeamForEvent(event);

		return true;
	}

	private void showBusinessTeamForEvent(RunwayEvent event) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.println("\nModels");
		for (RunwayEventTeamMember model : event.businessTeam.models) {
			System.out.println("Model - " + model.name + " - " + formatter.format(model.salary));
		}
		System.out.println("\nSupporting Staff");
		for (RunwayEventTeamMember staff : event.businessTeam.supportingStaff) {
			System.out.println("Supporting Staff - " + staff.name + " - " + formatter.format(staff.salary));
		}
		System.out.println("\nManagers");
		for (RunwayEventTeamMember manager : event.businessTeam.managers) {
			System.out.println("Manager - " + manager.name + " - " + formatter.format(manager.salary));
		}
		System.out.println("\nPhotographers");
		for (RunwayEventTeamMember photographer : event.businessTeam.photographers) {
			System.out.println("Photographer - " + photographer.name + " - " + formatter.format(photographer.salary));
		}
		System.out.println("\nDesigners");
		for (RunwayEventTeamMember designer : event.businessTeam.designers) {
			System.out.println("Designer - " + designer.name + " - " + formatter.format(designer.salary));
		}
		System.out.println("\nInfluencers");
		for (RunwayEventTeamMember influencer : event.businessTeam.influencers) {
			System.out.println("Influencer - " + influencer.name + " - " + formatter.format(influencer.salary));
		}
		System.out.println("\nThis business team will cost "
				+ formatter.format(event.businessTeam.calculateFullSalary()) + " to hire for this runway event.\n");
	}

	/**
	 * lists all runway events scheduled
	 */
	public void showRunwayEvents() {
		System.out.println();
		for (RunwayEvent event : getRunwayEvents()) {
			showRunwayEvent(event);
			System.out.println();
		}

	}

	// helper method for showRunwayEvents
	private void showRunwayEvent(RunwayEvent event) {
		System.out.println("Date: " + event.date);
		System.out.println("Line: " + event.line.getName());
		System.out.println("Duration: " + event.duration + " hours");
		System.out.println("Venue: " + event.venue);
		System.out.println("Catering Company: " + event.cateringCompany);
		System.out.print("Unpaid Invitees: ");
		for (String invitee : event.unpaidInvitees) {
			System.out.print(invitee + ", ");
		}

		System.out.println("\nTicket Price: $" + event.ticketPrice);

	}

	// helper method to display all dates valid for a runway event to be
	// scheduled
	// on
	private void showDates(RunwayEventDate[] dates) {
		System.out.println("List of all valid dates to schedule a runway event on: \n");
		for (int i = 0; i < dates.length; i++) {
			if (!dates[i].booked) {
				System.out.println(dates[i].date);
			}
		}
		System.out.println();
	}

	// generates the inital list of dates for runway events to be scheduled on
	private RunwayEventDate[] generateDates() {
		RunwayEventDate[] dates = new RunwayEventDate[10];
		for (int i = 0; i < dates.length; i++) {
			String daystring;
			int day = i + 15;
			if (day < 10) {
				daystring = "0" + day;
			} else {
				daystring = "" + day;
			}
			String date = "11/" + daystring + "/19";
			dates[i] = new RunwayEventDate(date, false);
		}
		return dates;
	}

	public List<RunwayEvent> getRunwayEvents() {
		return runwayEvents;
	}

	public void setRunwayEvents(List<RunwayEvent> runwayEvents) {
		this.runwayEvents = runwayEvents;
	}
}
