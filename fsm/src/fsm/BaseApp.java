package fsm;

import java.text.*;
import java.util.*;
import runway.*;
import fsm.sales.*;
import MenuItems.*;

/**
 * 
 * @author ejshouse
 * @author jwflory
 * @author jmedlund
 */
public class BaseApp {

	/**
	 * Guides the user through dialogue menu through various features of the app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		double [] a = {3,4,5};
		median(a);
		Scanner in = new Scanner(System.in);
		InputController.setScanner(in);
		PhotoshootController pc = new PhotoshootController();
		RunwayEventController rw = new RunwayEventController();
		SalesController sc = new SalesController();
		
		// Start adding menu items to main menu 
		ArrayList<iMenuItem> menuItems = new ArrayList<iMenuItem>();
		menuItems.add(new BlankMenuItem("--- Welcome to FSM 1.0 ---",false,false));
		menuItems.add(new BlankMenuItem("Test Item 1",true));
		menuItems.add(new BlankMenuItem("Test Item 2",true));
		menuItems.add(new BlankMenuItem("Test Item 4",true));
		menuItems.add(new BlankMenuItem("Test Item 3",true));
		
		// Iteration 1

		menuItems.add(new ManageInventory());
		
		
		// Iteration 2
		
		menuItems.add(new ManageLeads(sc));
		
		
		
		Menu mm = new Menu(menuItems, "Main Menu");
		mm.addItem(menuItems.size()-1,new UpdateMenu(mm));
		
		
		while (true) {
			System.out.println("Press 0 to quit");
			System.out.println("Press 1 to schedule a photoshoot");
			System.out.println("Press 2 to cancel a photoshoot");
			System.out.println("Press 3 to list all photoshoots");
			System.out.println("Press 4 to create a new fashion line");
			System.out.println("Press 5 to upload a new item");
			System.out.println("---------------------------");//line break to distinguish iter 1 functionality with iter 2 functionality
			System.out.println("Press 8 to schedule a runway event");
			System.out.println("Press 9 to list all runway events");
			System.out.println("Press 10 to create a business team for a runway event");
			System.out.println("Press 11 to list a business team for a runway event");
			System.out.println("Press 12 to manage or add an employee");
			System.out.println("Press 13 to curate outfits for a runway event");
			
			String input = in.nextLine();
			if (input.equals("0")) {
				break;	
			} else if (input.equals("1")) {
				boolean saved = pc.schedulePhotoshoot();
				if (saved)
					System.out.println("Your photoshoot has been saved\n\n");
				// error handling is done in app
			} else if (input.equals("2")) {
				pc.cancelPhotoshoot();
				System.out.println();
			} else if (input.equals("3")) {
				pc.showPhotoshoots();
				System.out.println();
			} else if (input.equals("4")) {
				// add line
				System.out.println("Enter new line name:");
				String lineName = in.nextLine();
				System.out.println("Enter estimated launch date: (mm/dd/yy)");
				String lineDate = in.nextLine();
				DateFormat df = new SimpleDateFormat("MM/dd/yy");
				try {
					if (FashionLineController.addLine(lineName, df.parse(lineDate))) {
						System.out.println("Line added successfully");
					}else{
						System.out.println("Line already exists");
					}
				} catch (ParseException e) {
					System.out.println("Invalid date format");
				}
				
				System.out.println("\n");
				System.out.println(DatabaseSupport.getLines());

			} else if (input.equals("5")) {
				// add item
				System.out.println("Enter item name:");
				String itemName = in.nextLine();
				
				System.out.println("Enter item type");
				String itemType = in.nextLine();
				
				System.out.println("Enter item fashion line");
				String itemLine = in.nextLine();
				
				double itemPrice;
				
				try{System.out.println("Enter item price:");
				System.out.print("$");
				itemPrice = Double.parseDouble(in.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Invalid price");
					continue;
				}
				
				if(ItemController.addItem(itemName, itemType, itemLine, itemPrice)){
					System.out.println("Item added successfully");
				}else{
					System.out.println("Item add failed. Check your name and fashion line and try again.\n");
				}
				
				System.out.println(DatabaseSupport.getItems());
				
			} else if (input.equals("8")){
				//schedule runway event
				rw.scheduleRunwayEvent();
			} else if (input.equals("9")) {
				rw.showRunwayEvents();
			} else if (input.equals("10")) {
				rw.createBusinessTeam();
			} else if (input.equals("11")) {
				rw.showBusinessTeamForEventDialog();
			} else if(input.equals("12")){
				hr.EmployeeManager.manageEmployee(in);
			} else if(input.equals("13")){
				OutfitCurator.curateOutfits(in, rw);
			} else {
				System.out.println("Invalid input");
			}
		}
		mm.execute();
		System.out.println("closing program");
		in.close();
	}

	
	public static double median(double[] d) {
		double median = Double.NaN;
		if (d != null & d.length > 0){
			if (d.length == 1) {
				median = d[0];
			} else {
				Arrays.sort(d);
				int mid = d.length / 2;
				if (d.length % 2 != 0) {
					median = d[mid];
				} else {
					median = (d[mid - 1] + d[mid]) / 2;
				}
			}
		}
		return median;
	}
}
