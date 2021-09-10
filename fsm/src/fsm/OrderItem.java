package fsm;

import java.util.Scanner;

import fsm.sales.Partner;

public class OrderItem {
	private int quantity;
	private double price;
	private Item item;
	private boolean isReady;
	
	public OrderItem(int q, double p, Item i) {
		this.quantity = q;
		this.price = p;
		this.item = i;
		this.isReady = false;
	}
	
	/**
	 * 
	 * @param db DatabaseSupport object
	 * @return new ShipmentItem corresponding to this OrderItem if the inventory
	 * is ready, null if there is not enough to fill the order
	 */
	public ShipmentItem verifyItem(Partner p) {
		WarehouseFloorItem whi = p.getFloorItemByItem(item);
		if(whi==null) {
			System.out.println("Item "+item.getName()+" does not exist on the floor. Skipping...");
			return null;
		}
		System.out.println(whi);
		if(quantity>whi.getQuantity()) {
			System.out.println("Not enough to fill order. Skipping...");
			return null;
		}
		Scanner n = new Scanner(System.in);
		String ans = "";
		while(!ans.equals("y") && !ans.equals("n")) {
			System.out.print("\nIs there enough of this item to ship? (y/n): ");
			ans = n.nextLine();
			ans = ans.toLowerCase();
			if(!ans.equals("y") && !ans.equals("n")) System.out.println("Please input 'y' or 'n'");
		}
		if(ans.equals("y")) {
			setReady(true);
			return new ShipmentItem(item, quantity);
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
			
			return null;
		}
	}
	
	public void setReady(boolean r) {
		this.isReady = r;
	}
	
	public boolean isReady() {
		return isReady;
	}
}