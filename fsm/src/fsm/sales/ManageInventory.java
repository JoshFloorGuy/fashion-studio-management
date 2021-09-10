package fsm.sales;

import java.util.ArrayList;

import MenuItems.BlankMenuItem;
import MenuItems.InputController;
import MenuItems.ListInventory;
import MenuItems.Menu;
import MenuItems.MenuDivide;
import MenuItems.iMenuItem;
import fsm.Address;
import fsm.DatabaseSupport;

/**
 * Menu for managing inventory
 * @author joshf
 *
 */
public class ManageInventory implements iMenuItem {
	private Menu m;
	private Partner partner;
	private ManageOrders op1;
	private ManageShipments op2;
	private ManageIncoming op3;
	private RequestInventory op4;
	private ListInventory op5;
	
	/**
	 * Creates a menu that will be called on execute
	 */
	public ManageInventory() {
		ArrayList<iMenuItem> items = new ArrayList<iMenuItem>();
		partner = new Partner(1);
		items.add(new BlankMenuItem("- Manage Orders and Inventory -", false));
		op1 = new ManageOrders(partner);
		op2 = new ManageShipments(partner);
		op3 = new ManageIncoming(partner);
		op4 = new RequestInventory(partner);
		op5 = new ListInventory(partner);
		items.add(op1);
		items.add(op2);
		items.add(op3);
		items.add(op4);
		items.add(op5);
		m = new Menu(items, "Manage Inventory and Orders");
		m.addItem(new MenuDivide());
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return m.getName();
	}
	
	/**
	 * Once user selects a location, they can choose to
	 * -Manage incoming orders
	 * -Manage outgoing shipments
	 * -Manage incoming shipments
	 * -Request inventory
	 * -List inventory
	 */
	public void execute() {
		if(DatabaseSupport.getPartners().size()<5) {
			DatabaseSupport.addPartner(new Partner("mrmoneyman@gmail.com",new Address("88 street st", "", "Ames", "IA", "USA", "50014"),"Storefront"));
			DatabaseSupport.addPartner(new Partner("dmstyle@aol.com",new Address("320 22 st", "Suite 201", "Des Moines", "IA", "USA", "50314"),"Storefront"));
			DatabaseSupport.addPartner(new Partner("industria@yahoo.com",new Address("394 4th st", "", "Ankeny", "IA", "USA", "50214"),"Warehouse"));
			DatabaseSupport.addPartner(new Partner("whglobal@whbl.net",new Address("12 3rd st", "", "Waterloo", "IA", "USA", "50912"),"Warehouse"));
			DatabaseSupport.addPartner(new Partner("yourrelibal@net.com",new Address("43 industry tee", "", "Detroit", "MI", "USA", "33322"),"Manufacturing"));
		}
		boolean picked = false;
		int id = 0;
		while(!picked) {
			id = InputController.promptInteger("Please enter the location ID:",1,Integer.MAX_VALUE);
			Partner t = new Partner(id);
			t = DatabaseSupport.getPartner(t);
			if(t==null) {
				System.out.println("Location '"+id+"' does not exist.");
			} else {
				picked=true;
				partner = t;
				if(m.size()==8) {
					if(partner.getType().equals("Manufacturing")) m.deleteItem(op4);
				} else {
					if(!partner.getType().equals("Manufacturing")) m.addItem(4, op4);
				}
				op1.setPartner(partner);
				op2.setPartner(partner);
				op3.setPartner(partner);
				op4.setPartner(partner);
				op5.setPartner(partner);
			}
		}
		m.execute();
	}
}
