package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;

import MenuItems.InputController;
import MenuItems.iMenuItem;
import fsm.DatabaseSupport;
import fsm.Item;
import fsm.Order;
import fsm.OrderItem;

/**
 * Lets user request Inventory at a given
 * partner location
 * @author joshf
 *
 */
public class RequestInventory implements iMenuItem {
	private Partner p;
	
	public RequestInventory(Partner p) {
		this.p = p;
	}
	
	/**
	 * Sets the partner to a new value
	 * @param p New partner
	 */
	public void setPartner(Partner p) {
		this.p = p;
	}
	
	/**
	 * Loops through all items, and asks the user how
	 * much they would like of each item.
	 * 
	 * If the partner location is a Manufacturing facility,
	 * they cannot request inventory.
	 */
	public void execute() {
		if(p.getType().equals("Manufacturing")) {
			System.out.println("Manufacturing facilities cannot request inventory.");
		}
		Iterator<Item> i = DatabaseSupport.getItems().iterator();
		ArrayList<OrderItem> oi = new ArrayList<OrderItem>();
		double price = 0;
		while(i.hasNext()) {
			Item j = i.next();
			int num = InputController.promptInteger("How many "+j.getName()+" would you like? (0 for none): ", 0, Integer.MAX_VALUE);
			if(num>0) {
				price+=(num*j.getPrice());
				oi.add( new OrderItem(num, num*j.getPrice(), j));
			}
		}
		if(price>0) {
			DatabaseSupport.addOrder(new Order(p.getRecipient(),price, oi));
			System.out.println("Request added");
		}
	}

	public String getName() {
		return "Request Inventory";
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
}
