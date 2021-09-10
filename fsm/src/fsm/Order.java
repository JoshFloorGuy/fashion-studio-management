package fsm;

import java.util.ArrayList;

import fsm.sales.Partner;

public class Order {
	private String orderID;
	private Recipient recipient;
	private double price;
	private ArrayList<OrderItem> itemList;
	private String status;
	
	public Order(String o, Recipient r, double p, ArrayList<OrderItem> i) {
		this.orderID = o;
		this.recipient = r;
		this.price = p;
		this.itemList = i;
		this.status = "open";
	}
	
	/**
	 * Represents a customer order. This contains a list of items that the customer
	 * has ordered, a price for all of the items, and an object that represents the
	 * customer contact information.
	 * @param r Customer contact information
	 * @param p Price of the order
	 * @param i List of items that customer ordered
	 */
	public Order(Recipient r, double p, ArrayList<OrderItem> i) {
		String id = "";
		boolean exists = true;
		while(exists) {
			id="";
			for(int a=0;a<5;a++) {
				int b = (int) (Math.floor(Math.random()*10));
				id+=(("0123456789").charAt(b)+"");
			}
			exists = DatabaseSupport.orderExists(new Order(id));
		}
		this.orderID = id;
		this.recipient = r;
		this.price = p;
		this.itemList = i;
		this.status = "Open";
	}
	
	public Order(String o) {
		this(o,(Recipient) null, 0.0, (ArrayList<OrderItem>) null);
	}
	
	public String getOrderID() {
		return orderID;
	}
	
	public String getOrderStatus() {
		return status;
	}
	
	public void setOrderStatus(String s) {
		status = s;
	}
	
	/**
	 * Creates a shipment that is ready to be packed if there is at least 1
	 * item in stock.
	 * @return Shipment with all items that are ready to go
	 */
	public Shipment verifyShipment(Partner p) {
		ArrayList<ShipmentItem> newItems = new ArrayList<ShipmentItem>();
		int tested = 0;
		int ready = 0;
		ShipmentItem a;
		System.out.println("Verifying order "+orderID);
		for(int i = 0; i<itemList.size(); i++) {
			a = null;
			if(!itemList.get(i).isReady()) {
				tested++;
				a = itemList.get(i).verifyItem(p);
				if(a!=null) {
					ready++;
					newItems.add(a);
				}
			}
		}
		if(ready>0) {
			if(tested==ready && status.equals("Open")) status = "Preparing";
			return new Shipment(this, p, newItems);
		}
		return null;
	}
	
	public String printAddress() {
		return recipient.toString();
	}
	
	public Recipient getRecipient() {
		return recipient;
	}
}
