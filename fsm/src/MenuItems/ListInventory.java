package MenuItems;

import java.util.Iterator;

import fsm.WarehouseFloorItem;
import fsm.sales.Partner;

public class ListInventory implements iMenuItem {
	private Partner p;

	public ListInventory(Partner p) {
		this.p = p;
	}
	
	public void setPartner(Partner p) {
		this.p = p;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "View Inventory";
	}
	
	/**
	 * Shows a list of inventory items
	 */
	public void execute() {
		System.out.println();
		Iterator<WarehouseFloorItem> i = p.getFloorItems().iterator();
		if(!i.hasNext()) {
			System.out.println("inventory is empty");
		}
		while(i.hasNext()) {
			System.out.println(i.next()+"\n");
		}
	}
}
