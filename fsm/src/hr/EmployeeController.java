package hr;

public class EmployeeController {
	public static boolean fire(Employee e) {
		fsm.DatabaseSupport.removeEmployee(e);
		return true;
	}

	public static boolean demote(Employee e, String newTitle, double newPayRate) {
		boolean out = true;

		if (!newTitle.equals(""))
			e.setTitle(newTitle);

		if (newPayRate >= e.getPayRate()) {
			System.out.println(e.getName() + "'s pay just increased! can you really call this a demotion?");
			out = false;
		}
		e.setPayRate(newPayRate);

		return out;
	}

	public static boolean promote(Employee e, String newTitle, double newPayRate) {
		boolean out = true;

		if (!newTitle.equals(""))
			e.setTitle(newTitle);

		if (newPayRate < e.getPayRate()) {
			System.out.println(e.getName() + "'s pay just decreased! can you really call this a promotion?");
			out = false;
		}
		e.setPayRate(newPayRate);

		return out;
	}

	public static boolean reassign(Employee e, String newLocation) {
		e.setLocation(newLocation);
		return true;
	}
}
