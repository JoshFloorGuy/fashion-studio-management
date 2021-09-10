package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;

import MenuItems.InputController;
import MenuItems.iMenuItem;
import fsm.Address;
import fsm.DatabaseSupport;
import fsm.Item;
import fsm.Order;
import fsm.OrderItem;
import fsm.Recipient;
import fsm.Shipment;

/**
 * Lets user manage incoming orders
 * @author joshf
 *
 */
public class ManageOrders implements iMenuItem {
	Partner p;
	
	public ManageOrders(Partner p) {
		this.p = p;
	}
	
	public void setPartner(Partner p) {
		this.p = p;
	}
	
	/**
	 * Starts Orders menu. All orders shown are orders that
	 * are ready to be packed.
	 */
	public void execute() {
		if(DatabaseSupport.getOrders().size()==0) {
			ArrayList<OrderItem> list = new ArrayList<OrderItem>();
			Iterator<Item> a = DatabaseSupport.getItems().iterator();
			Item i;
			int j;
			double k = 0;
			while(a.hasNext()) {
				i = a.next();
				j = (int) (Math.random()*10)+1;
				k += (i.getPrice()*j);
				list.add(new OrderItem(j,(i.getPrice()*j),i));
			}
			Recipient r = new Recipient("John Johnathan Johnson", "jjjthejmancan@gmail.com",new Address(
					"123 fake street", "apt 32", "Ames", "IA", "USA", "50014"
				));
			DatabaseSupport.addOrder(new Order(r, k, list));
		}
		
		Iterator<Order> i = DatabaseSupport.getOrders(p).iterator();
		Order temp;
		ArrayList<Order> tempList = new ArrayList<Order>();
		int a = 0;
		System.out.println("Orders ready to check:");
		while(i.hasNext()) {
			temp = i.next();
			if(!temp.getOrderStatus().equals("Shipped") && !temp.getOrderStatus().equals("Preparing") && !temp.getOrderStatus().equals("Shipped")) {
				a++;
				tempList.add(temp);
				System.out.println(temp.getOrderID()+" - "+temp.getOrderStatus());
			}
		}
		if(a==0) {
			System.out.println("There are no orders ready to check.");
		} else {
			String ans = "";
			Order s = null;
			while(ans.length()==0) {
				ans = InputController.promptString("Start checking which order? (q to quit)");
				if(!ans.equals("q")) {
					i = tempList.iterator();
					boolean found = false;
					while(!found && i.hasNext()) {
						s = i.next();
						found = s.getOrderID().equals(ans);
					}
					if(!found) {
						ans = "";
						InputController.promptString("That order ID does not exist, or is already shipped.\nPress any key to continue.");
						System.out.println("Current orders ready to check:");
						i = tempList.iterator();
						Order t;
						while(i.hasNext()) {
							t = i.next();
							System.out.println(t.getOrderID()+" - "+t.getOrderStatus());
						}
					}
				}
			}
			if(!ans.equals("q")) {
				Shipment newShipment = s.verifyShipment(p);
				if(newShipment!=null) {
					System.out.println("A new shipment has been created for this order: "+newShipment.getShipmentID());
					DatabaseSupport.addShipment(newShipment);
				}
			}
		}
	}
	
	public String getName() {
		return "Manage Orders";
	}
	
	public boolean deletable() {
		return false;
	}
	
	public boolean isNumbered() {
		return true;
	}
}
