package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;

import MenuItems.InputController;
import MenuItems.iMenuItem;
import fsm.DatabaseSupport;
import fsm.Item;
import fsm.Shipment;
import fsm.ShipmentItem;
import fsm.WarehouseFloorItem;

/**
 * Lets user manage incoming inventory
 * @author joshf
 *
 */
public class ManageIncoming implements iMenuItem {
	private Partner p;
	
	public ManageIncoming(Partner p) {
		this.p = p;
	}
	
	public void setPartner(Partner p) {
		this.p = p;
	}
	
	/**
	 * If the partner is a manufacturing facility,
	 * they get to manually add every item they make.
	 * 
	 * If not, the partner is shown shipments that are
	 * incoming and have been marked as "Shipping". The
	 * user can then load all the items in these orders.
	 */
	public void execute() {
		if(p.getType().equals("Manufacturing")) {
			ArrayList<Item> items = DatabaseSupport.getItems();
			Iterator<Item> it = items.iterator();
			Item i;
			while(it.hasNext()) {
				i = it.next();
				int a = InputController.promptInteger("How many "+i.getName()+" were manufactured? (0 for none)", 0, Integer.MAX_VALUE);
				if(a>0) {
					WarehouseFloorItem n = p.getFloorItemByItem(i);
					if(n==null) {
						String newFloorLocation = InputController.promptString("This item does not have a floor location.\nPlease specify a new one for this item.");
						p.addFloorLocation(new WarehouseFloorItem(newFloorLocation, i, a));
					} else {
						n.setQuantity(n.getQuantity()+a);
					}
				}
			}
		} else {
			ArrayList<Shipment> shipments = DatabaseSupport.getIncomingOrders(p);
			if(shipments.size()>0) {
				Iterator<Shipment> i;
				int k = -1;
				Shipment a;
				while(k!=0) {
					i = shipments.iterator();
					k = 0;
					while(i.hasNext()) {
						a = i.next();
						k++;
						System.out.println(k+". "+a.getShipmentID()+" - "+a.getOrderSize()+" different items");
					}
					k = InputController.promptInteger("Please enter the number next to the shipment ID to\nselect a shipment to handle (0 to quit)",0,shipments.size());
					if(k!=0) {
						a = shipments.get(k);
						Iterator<ShipmentItem> b = a.getShipmentItems().iterator();
						ShipmentItem c;
						String temp;
						while(b.hasNext()) {
							c = b.next();
							WarehouseFloorItem d = p.getFloorItemByItem(c.getItem());
							if(d==null) {
								temp = InputController.promptString(c.getItem().getName()+" does not have a floor location yet.\nPlease input a floor location for it.");
								d = new WarehouseFloorItem(temp, c.getItem(),0);
							}
							temp = InputController.promptString("\nItem: "+c.getItem().getName()+"\nQuantity: "+c.getQuantity()+"\n\nPress enter once you are done loading this item.");
							d.setQuantity(d.getQuantity()+c.getQuantity());
						}
						k=0;
						a.setShipped();
						System.out.println("\nShipment "+a.getShipmentID()+" loaded successfully\n");
					}
				}
			} else {
				System.out.println("There are no incoming shipments.");
			}
		}
	}

	public String getName() {
		return "Manage Incoming Inventory";
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
}
