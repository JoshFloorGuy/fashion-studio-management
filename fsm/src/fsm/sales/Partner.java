package fsm.sales;

import java.util.ArrayList;

import fsm.Address;
import fsm.Item;
import fsm.Recipient;
import fsm.WarehouseFloorItem;

/**
 * This represents and partner location, whether it be
 * a manufacturer, a warehouse, or a storefront.
 * 
 * Each partner will have an inventory, an ID, a recipient,
 * and a type.
 * @author joshf
 *
 */
public class Partner {
	private int ID;
	private Recipient r;
	private ArrayList<WarehouseFloorItem> inventory;
	private String type;
	
	public Partner(String email, Address a, String t) {
		if(t.equals("Manufacturing") || t.equals("Storefront")) {
			type = t;
		} else {
			type = "Warehouse";
		}
		inventory = new ArrayList<WarehouseFloorItem>();
		r = new Recipient(type+" Location", email, a);
	}
	
	public Partner(Partner p, int ID) {
		this.ID = ID;
		r = p.r;
		inventory = p.inventory;
		type = p.type;
	}
	
	public Partner(int ID) {
		this.ID = ID;
		r = null;
		inventory = null;
		type = null;
	}
	
	public Partner(String email, Address a) {
		this(email,a,"");
	}
	
	public int getPartnerID() {
		return ID;
	}
	
	public String getType() {
		return type;
	}
	
	public Recipient getRecipient() {
		return r;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!o.getClass().equals(this.getClass())) return false;
		return (((Partner) o).ID == ID);
	}
	
	/**
	 * Gets the floor item that has the given Item
	 * @param item Item that we are searching for
	 * @return the WarehouseFloorItem that contains item if 
	 * it exists, null if it does not
	 */
	public WarehouseFloorItem getFloorItemByItem(Item item) {
		for(int i = 0; i<inventory.size(); i++) {
			if(item.getName().equals(inventory.get(i).getName())) return inventory.get(i);
		}
		return null;
	}
	
	/**
	 * @return a list of all WarehouseFloorItems
	 */
	public ArrayList<WarehouseFloorItem> getFloorItems() {
		return inventory;
	}
	
	/**
	 * @param location The location in question
	 * @return True if there is a WarehouseFloorItem with this
	 * location, false if there is not.
	 */
	public boolean floorLocationExists(WarehouseFloorItem location) {
		for(int i=0; i<inventory.size(); i++) {
			if(location.getFloorLocation().equals(inventory.get(i).getFloorLocation())) return true;
		}
		return false;
	}
	
	/**
	 * Adds a floor location to the inventory
	 * @param location floor location to be added
	 */
	public void addFloorLocation(WarehouseFloorItem location) {
		inventory.add(location);
	}
}
