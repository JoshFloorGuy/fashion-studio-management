package MenuItems;

public class MoveMenuItem implements iMenuItem{
	private Menu m;
	
	public MoveMenuItem(Menu m) {
		this.m = m;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "Move Item";
	}
	
	/**
	 * Asks the user for an item and a new index, and moves the
	 * item there.
	 */
	public void execute() {
		m.displayMenu(true,false);
		int i = InputController.promptInteger("Please enter the index of the item you want to move",1,m.size());
		iMenuItem k = m.getItem(i-1);
		m.deleteItem(k);
		m.displayMenu(true);
		i = InputController.promptInteger("Please enter the new index of this item",1,m.size()+1);
		if(i>m.size()) {
			m.addItem(k);
		} else {
			m.addItem(i-1,k);
		}
	}
}
