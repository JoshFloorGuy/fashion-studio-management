package MenuItems;

/**
 * Adds a Menu Divide where the user specifies
 * @author joshf
 *
 */
public class AddMenuDivide implements iMenuItem {
	private Menu m;
	
	public AddMenuDivide(Menu m) {
		this.m = m;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "Add Divide";
	}
	
	public void execute() {
		m.displayMenu(true);
		int i = InputController.promptInteger("Please enter the index of this new item",1,m.size()+1);
		MenuDivide n = new MenuDivide();
		if(i>m.size()) {
			m.addItem(n);
		} else {
			m.addItem(i-1,n);
		}
	}
}
