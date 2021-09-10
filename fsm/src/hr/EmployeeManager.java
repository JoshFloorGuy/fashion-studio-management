package hr;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {

	public static void manageEmployee(Scanner s) {
		System.out.println("Select an option:");
		System.out.println("1 -- Add a new hire");

		ArrayList<Employee> people = fsm.DatabaseSupport.getEmployees();
		String name;
		for (int i = 0; i < people.size(); i++) {
			name = people.get(i).getName();
			System.out.println((i + 2) + " -- manage " + name);
		}

		boolean succ = false;
		int choice = 0;
		while (!succ) {
			try {
				choice = Integer.parseInt(s.nextLine());
				succ = true;
				if (choice <= 0 || choice > people.size() + 1) {
					System.out.println("Please enter a valid choice");
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid choice");
			}
		}

		if (choice == 1)
			hireEmployee(s);
		else {
			manage(s, people.get(choice - 2));
		}
	}

	private static void hireEmployee(Scanner s) {
		String name, title, location;
		double payRate = 0.00;
		System.out.println("Enter hiree's name:");
		name = s.nextLine();
		System.out.println("Enter hiree's title:");
		title = s.nextLine();
		System.out.println("Enter hiree's location:");
		location = s.nextLine();

		boolean succ = false;
		while (!succ) {
			System.out.print("Enter hiree's pay rate:\n$");
			try {
				payRate = Double.parseDouble(s.nextLine());
				succ = true;
			} catch (Exception e) {
				System.out.println("Invalid format. Please enter a number.");
			}
		}

		fsm.DatabaseSupport.addEmployee(new Employee(name, title, location, payRate));
	}

	private static void manage(Scanner s, Employee e) {
		System.out.println("Select an option:");
		System.out.println("1 -- Fire " + e.getName());
		System.out.println("2 -- Demote " + e.getName());
		System.out.println("3 -- Promote " + e.getName());
		System.out.println("4 -- Reassign " + e.getName());
		System.out.println("5 -- Cancel");

		int choice = 0;
		boolean succ = false;
		while (!succ) {
			try {
				choice = Integer.parseInt(s.nextLine());
				if (choice < 1 || choice > 5) {
					System.out.println("Please enter a valid choice");
				} else {
					succ = true;
				}
			} catch (Exception ex) {
				System.out.println("Please enter an integer choice");
			}
		}

		String newTitle = "";
		double newPayRate = 0.00;

		switch (choice) {
		case 1: // Fire
			EmployeeController.fire(e);
			System.out.println("Fired " + e);
			System.out.println("You should probably let them know");
			break;
		case 2: // Demotion
			System.out.println("Enter employee's new title or blank to skip");
			newTitle = s.nextLine();

			System.out.println("Enter employee's new pay rate");

			succ = false;
			while (!succ) {
				try {
					newPayRate = Integer.parseInt(s.nextLine());
					succ = true;
				} catch (Exception ex) {
					System.out.println("Please enter a number");
				}
			}

			EmployeeController.demote(e, newTitle, newPayRate);

			System.out.println("Demoted " + e);
			break;
		case 3:

			System.out.println("Enter employee's new title or blank to skip");
			newTitle = s.nextLine();

			System.out.println("Enter employee's new pay rate");

			succ = false;
			while (!succ) {
				try {
					newPayRate = Double.parseDouble(s.nextLine());
					succ = true;
				} catch (Exception ex) {
					System.out.println("Please enter a number");
				}
			}

			EmployeeController.promote(e, newTitle, newPayRate);
			System.out.println("Promoted " + e);
			break;
		case 4: // reassignment

			System.out.println("Enter employee's new location");
			String newLocation = s.nextLine();

			EmployeeController.reassign(e, newLocation);

			System.out.println("Employee added: " + e);
			break;
		default:
		}
	}
}
