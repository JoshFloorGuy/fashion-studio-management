package MenuItems;

/**
 * Allows user to add a menu item
 * @author joshf
 *
 */
public class AddMenuItem implements iMenuItem {
	private Menu m;
	
	public AddMenuItem(Menu m) {
		this.m = m;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "Add Item";
	}
	
	public void execute() {
		String newName = InputController.promptString("Please enter the display name");
		boolean isNumbered = InputController.promptYesNo("Is this item numbered?");
		m.displayMenu(true);
		int i = InputController.promptInteger("Please enter the index of this new item",1,m.size()+1);
		BlankMenuItem n = new BlankMenuItem(newName, isNumbered);
		if(i>m.size()) {
			m.addItem(n);
		} else {
			m.addItem(i-1,n);
		}
	}
}
