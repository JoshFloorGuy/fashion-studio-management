package fsm;

import java.util.ArrayList;

import fsm.sales.Partner;

public class Shipment {
	private Order order;
	private ArrayList<ShipmentItem> itemList;
	private String shipmentID;
	private boolean shipped;
	private Partner p;

	public Shipment(Order o, Partner p, ArrayList<ShipmentItem> i) {
		this.p = p;
		String id="";
		boolean exists = true;
		while(exists) {
			id="";
			for(int a=0;a<8;a++) {
				int b = (int) (Math.round(Math.random()*8));
				id+=(("012345679").charAt(b)+"");
			}
			exists = DatabaseSupport.shipmentExists(new Shipment(id));
		}
		this.order = o;
		this.itemList = i;
		this.shipmentID = id;
		this.shipped = false;
	}
	
	public Shipment(String id) {
		this.shipmentID = id;
		this.itemList = null;
		this.order = null;
		this.shipped = false;
	}
	
	public String getShipmentID() {
		return shipmentID;
	}
	
	public boolean isShipped() {
		return shipped;
	}

	public Shipment packShipment() {
		ArrayList<ShipmentItem> newItems = new ArrayList<ShipmentItem>();
		int tested = 0;
		int ready = 0;
		ShipmentItem a;
		System.out.println("Verifying shipment "+shipmentID);
		for(int i = 0; i<itemList.size(); i++) {
			a = null;
			if(!itemList.get(i).isShipped()) {
				tested++;
				a = itemList.get(i).verifyItem(p);
				if(a!=null) {
					ready++;
					newItems.add(a);
				}
			}
		}
		if(ready!=tested){
			System.out.println("Shipment saved successfully.\nCustomer info:\n"+order.printAddress()+"\n\nOrder ID: "+order.getOrderID()+"\nShipment ID: "+shipmentID);
			shipped = true;
		} else {
			System.out.println("No items are in the order, order is not shipped");
		}
		if(ready>0) {
			if(tested!=ready && order.getOrderStatus().equals("Preparing")) order.setOrderStatus("Partially Shipped");
			return new Shipment(order, p, newItems);
		} else {
			order.setOrderStatus("Shipping");
		}
		return null;
	}
	
	public Partner getPartner() {
		return p;
	}
	
	public Recipient getRecipient() {
		return order.getRecipient();
	}
	
	public String getStatus() {
		return order.getOrderStatus();
	}
	
	public int getOrderSize() {
		return itemList.size();
	}
	
	public ArrayList<ShipmentItem> getShipmentItems() {
		return itemList;
	}
	
	public void setShipped() {
		order.setOrderStatus("Shipped");
	}
}
