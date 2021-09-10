package fsm;

import java.util.ArrayList;
import java.util.Iterator;

import fsm.sales.Partner;

/**
 * @author ejshouse
 * @author jmedlund
 * @author jwflory
 *
 */

public class DatabaseSupport {
	private static ArrayList<Item> items = new ArrayList<Item>();
	private static ArrayList<FashionLine> lines = new ArrayList<FashionLine>();
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private static ArrayList<Shipment> shipments = new ArrayList<Shipment>();
	private static ArrayList<hr.Employee> employees = new ArrayList<hr.Employee>();
	private static ArrayList<Partner> partners = new ArrayList<Partner>();
	
	public static boolean exists(FashionLine line){
		
		for(int i=0; i<lines.size(); i++){
			if(line.getName().equals(lines.get(i).getName())){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean exists(Item item){
		
		for(int i=0; i<items.size(); i++){
			if(item.getName().equals(items.get(i).getName())){
				return true;
			}
		}		
		return false;
	}

	public static ArrayList<Item> getItems(){
		return items;
	}
	
	public static ArrayList<Item> getItemsByLine(FashionLine line)
	{
		ArrayList<Item> itemsInLine = new ArrayList<>();
		for (Item item: items)
		{
			if (item.getLine().equals(line))
			{
				itemsInLine.add(item);
			}
		}
		return itemsInLine;
	}
	
	public static ArrayList<FashionLine> getLines(){
		return lines;
	}
	
	public static boolean orderExists(Order order) {
		for(int i=0; i<orders.size(); i++) {
			if(order.getOrderID().equals(orders.get(i).getOrderID())) return true;
		}
		return false;
	}
	
	public static ArrayList<Order> getOrders(Partner p) {
		ArrayList<Order> o = new ArrayList<Order>();
		Order a;
		Iterator<Order> i = orders.iterator();
		while(i.hasNext()) {
			a = i.next();
			switch(p.getType().toLowerCase()) {
				case "manufacturing":
					if(a.getRecipient().getName().equals("Warehouse Location")) o.add(a);
					break;
				case "warehouse":
					if(!a.getRecipient().equals(p.getRecipient()) && !a.getRecipient().getName().equals("Manufacturing Location")) o.add(a);
					break;
				case "storefront":
					if(!a.getRecipient().getName().equals("Warehouse Location") && !a.getRecipient().getName().equals("Manufacturing Location")) o.add(a);
					break;
				default:
					break;
			}
		}
		return o;
	}
	
	public static ArrayList<Shipment> getIncomingOrders(Partner p) {
		ArrayList<Shipment> o = new ArrayList<Shipment>();
		Shipment a;
		Iterator<Shipment> i = shipments.iterator();
		while(i.hasNext()) {
			a = i.next();
			if(a.getRecipient().equals(p.getRecipient()) && a.getStatus().equals("Shipping")) o.add(a);
		}
		return o;
	}
	
	public static ArrayList<Order> getOrders() {
		return orders;
	}
	
	public static ArrayList<Shipment> getShipments() {
		return shipments;
	}
	
	public static ArrayList<Shipment> getShipments(Partner p) {
		ArrayList<Shipment> o = new ArrayList<Shipment>();
		Shipment a;
		Iterator<Shipment> i = shipments.iterator();
		while(i.hasNext()) {
			a = i.next();
			if(a.getPartner().equals(p)) o.add(a);
		}
		return o;
	}
	
	public static boolean shipmentExists(Shipment shipment) {
		for(int i=0; i<shipments.size(); i++) {
			if(shipment.getShipmentID().equals(shipments.get(i).getShipmentID())) return true;
		}
		return false;
	}
	
	public static void addShipment(Shipment s) {
		shipments.add(s);
	}
	
	public static void addOrder(Order o) {
		orders.add(o);
	}
	
	public static void addEmployee(hr.Employee e){
		
		employees.add(e);
	}
	
	public static void removeEmployee(hr.Employee e){
		employees.remove(e);
	}
	
	public static ArrayList<hr.Employee> getEmployees(){
		return employees;
	}
	
	public static void addPartner(Partner p) {
		int newID = (partners.size()==0) ? 1 : partners.get(partners.size()-1).getPartnerID()+1;
		partners.add(new Partner(p,newID));
	}
	
	public static ArrayList<Partner> getPartners() {
		return partners;
	}
	
	public static Partner deleteParter(Partner p) {
		int i = partners.indexOf(p);
		if(i<0) return null;
		return partners.remove(i);
	}
	
	public static Partner getPartner(Partner p) {
		int i = partners.indexOf(p);
		if(i<0) return null;
		return partners.get(partners.indexOf(p));
	}
}
