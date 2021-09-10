package fsm.sales;

import MenuItems.iMenuItem;

/**
 * Menu item for sc.manageLeads()
 * @author joshf
 *
 */
public class ManageLeads implements iMenuItem {
	SalesController sc;
	public ManageLeads(SalesController s) {
		sc = s;
	}
	
	public void execute() {
		sc.manageLeads();
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "Manage Sales Leads";
	}
}
