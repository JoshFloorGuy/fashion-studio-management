package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;

import MenuItems.InputController;
import MenuItems.iMenuItem;
import fsm.DatabaseSupport;
import fsm.Shipment;

/**
 * Lets user manage outgoing shipments
 * @author joshf
 *
 */
public class ManageShipments implements iMenuItem {
	private Partner p;
	
	public ManageShipments(Partner p) {
		this.p = p;
	}
	
	public void setPartner(Partner p) {
		this.p = p;
	}
	
	/**
	 * Starts the shipment management menu. Users will
	 * load orders here. All order the user can see are orders
	 * that are specific to the location.
	 */
	public void execute() {
		Iterator<Shipment> i = DatabaseSupport.getShipments(p).iterator();
		Shipment temp;
		ArrayList<Shipment> tempList = new ArrayList<Shipment>();
		int a = 0;
		System.out.println("Current shipments ready to pack:");
		while(i.hasNext()) {
			temp = i.next();
			if(!temp.isShipped()) {
				a++;
				tempList.add(temp);
				System.out.println(temp.getShipmentID());
			}
		}
		if(a==0) {
			System.out.println("There are no shipments ready to pack.");
		} else {
			String ans = "";
			Shipment s = null;
			while(ans.length()==0) {
				ans = InputController.promptString("Start packing which shipment? (q to quit)");
				if(!ans.equals("q")) {
					i = tempList.iterator();
					boolean found = false;
					while(!found && i.hasNext()) {
						s = i.next();
						found = s.getShipmentID().equals(ans);
					}
					if(!found) {
						ans = "";
						System.out.println("That shipment ID does not exist, or is not waiting to be packed.");
						System.out.println("Current shipments ready to pack:");
						i = tempList.iterator();
						while(i.hasNext()) {
							System.out.println(i.next().getShipmentID());
						}
					}
				}
			}
			if(!ans.equals("q")) {
				Shipment newShipment = s.packShipment();
				if(newShipment!=null) {
					System.out.println("A new shipment has been created for the unshipped items: "+newShipment.getShipmentID());
					DatabaseSupport.addShipment(newShipment);
				}
			}
		}
	}

	public String getName() {
		return "Manage Outgoing Shipments";
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
}
