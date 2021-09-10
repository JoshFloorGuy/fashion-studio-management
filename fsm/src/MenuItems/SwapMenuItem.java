package MenuItems;

public class SwapMenuItem implements iMenuItem {
	private Menu m;
	
	public SwapMenuItem(Menu m) {
		this.m = m;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "Swap Items";
	}
	
	/**
	 * Swaps 2 menu items
	 */
	public void execute() {
		m.displayMenu(true,false);
		int i = InputController.promptInteger("Please enter the index of the first item",1,m.size());
		iMenuItem k = m.getItem(i-1);
		m.displayMenu(true,false);
		int j = InputController.promptInteger("Please enter the index of the second item",1,m.size());
		iMenuItem l = m.getItem(j-1);
		m.swapItems(k, l);
	}
}
