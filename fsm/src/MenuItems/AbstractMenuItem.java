package MenuItems;

/**
 * 
 * @author jmedlund
 * Allows for some flexibility in the Menu Item pattern
 */

public class AbstractMenuItem implements iMenuItem {

	Executor e;
	String name;
	boolean numbered;
	boolean deletable;
	
	public AbstractMenuItem(String n, boolean in, boolean d, Executor e){
		name = n;
		numbered = in;
		this.e = e;
		deletable = d;
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		e.execute();

	}

	@Override
	public boolean isNumbered() {
		return numbered;
	}
	
	@Override
	public boolean deletable() {
		return deletable;
	}

	public interface Executor{
		public void execute();
	}
}



//Example:
//menu.addItem(new AbstractMenuItem("Add Employee", true, () -> {hr.EmployeeManager.manageEmployee(in);}));
