package fsm;

import java.util.Scanner;

import fsm.sales.Partner;

public class ShipmentItem {
	private Item item;
	private int quantity;
	private boolean isShipped;
	
	public ShipmentItem(Item i, int q) {
		this.item = i;
		this.quantity = q;
		this.isShipped = false;
	}
	
	public ShipmentItem verifyItem(Partner p) {
		WarehouseFloorItem whi = p.getFloorItemByItem(item);
		if(whi==null) {
			System.out.println("Item "+item.getName()+" does not exist on the floor. Skipping...");
			return null;
		}
		System.out.println(whi);
		Scanner n = new Scanner(System.in);
		String ans = "";
		while(!ans.equals("y") && !ans.equals("n")) {
			System.out.print("\nIs there enough of this item to ship? (y/n): ");
			ans = n.nextLine();
			ans = ans.toLowerCase();
			if(!ans.equals("y") && !ans.equals("n")) System.out.println("Please input 'y' or 'n'");
		}
		if(ans.equals("y")) {
			setShipped(true);
			whi.setQuantity(whi.getQuantity()-quantity);
			return null;
		} else {
			int a = -1;
			while(a<0) {
				System.out.print("Current product qty: ");
				try {
					a = n.nextInt();
					if(a<0) {
						System.out.println("Please enter an integer greater than or equal to 0");
					}
				} catch(Exception e) {
					a = -1;
					n.nextLine();
					System.out.println("Please enter an integer");
				}
			}
			
			return this;
		}
	}
	
	public void setShipped(boolean r) {
		this.isShipped = r;
	}
	
	public boolean isShipped() {
		return isShipped;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Item getItem() {
		return item;
	}
}
