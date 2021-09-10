package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a potential customer lead found by
 * a marketing rep. Has a company name and a list
 * of possible contacts, as well as a meeting 
 * when one is set up.
 * @author joshf
 *
 */
public class Lead {
	private String company;
	private ArrayList<Contact> contacts;
	private Meeting meeting;
	private SalesRep salesrep;
	private int leadID;
	
	public Lead(int id, String n, ArrayList<Contact> c) {
		leadID = id;
		company = n;
		contacts = c;
		meeting = null;
		salesrep = null;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setSalesRep(SalesRep s) {
		salesrep = s;
	}
	
	public SalesRep getSalesRep() {
		return salesrep;
	}
	
	public void setMeeting(Meeting s) {
		meeting = s;
	}
	
	public Meeting getMeeting() {
		return meeting;
	}
	
	public int getLeadID() {
		return leadID;
	}
	
	@Override
	public String toString() {
		return leadID + ": " + company + ", "+((meeting==null) ? "not " : "")+"booked";
	}
	
	public void printContacts() {
		Iterator<Contact> k = contacts.iterator();
		while(k.hasNext()) {
			System.out.println("   "+k.next());
		}
	}
}
