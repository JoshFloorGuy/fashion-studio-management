package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Sales Representatives work queue,
 * containing a list of leads and upcoming meetings. The
 * main identifiers of this class are the name and the ID
 * 
 * @author joshf
 *
 */
public class SalesRep {
	private int salesRepID;
	private String name;
	private ArrayList<Lead> leads;
	private ArrayList<Meeting> meetings;
	
	public SalesRep(int id, String n, ArrayList<Lead> l, ArrayList<Meeting> m) {
		salesRepID = id;
		name = n;
		leads = l;
		meetings = m;
	}
	
	public SalesRep(int id, String n) {
		this(id, n, new ArrayList<Lead>(), new ArrayList<Meeting>());
	}
	
	/**
	 * Prints all the information for non-booked leads
	 * @return list of non-booked leads
	 */
	public ArrayList<Lead> printFullLeads() {
		Iterator<Lead> i = leads.iterator();
		Lead j;
		int num = 1;
		ArrayList<Lead> ret = new ArrayList<Lead>();
		while(i.hasNext()) {
			j = i.next();
			if(j.getMeeting()==null) {
				System.out.println(num+". "+j.getCompany());
				j.printContacts();
				ret.add(j);
				num++;
			}
		}
		return ret;
	}
	
	/**
	 * Prints all non-booked leads for manager
	 * @return list of non-booked leads
	 */
	public ArrayList<Lead> printManagableLeads() {
		Iterator<Lead> i = leads.iterator();
		Lead j;
		ArrayList<Lead> ret = new ArrayList<Lead>();
		while(i.hasNext()) {
			j = i.next();
			if(j.getMeeting()==null) {
				System.out.println(j);
				ret.add(j);
			}
		}
		return ret;
	}
	
	/**
	 * This prints off all the pending meetings (ones
	 * that have not been completed)
	 * @return A list of the pending meetings
	 */
	public ArrayList<Meeting> printMeetings() {
		Iterator<Meeting> i = meetings.iterator();
		Meeting j;
		int num = 1;
		ArrayList<Meeting> ret = new ArrayList<Meeting>();
		while(i.hasNext()) {
			j = i.next();
			if(!j.isMeetingDone()) {
				System.out.println(num+". "+j);
				ret.add(j);
				num++;
			}
		}
		return ret;
	}
	
	@Override
	public String toString() {
		return salesRepID+": "+name;
	}
	
	public int getSalesRepID() {
		return salesRepID;
	}
	
	public String getName() {
		return name;
	}
	
	public void removeLead(Lead l) {
		leads.remove(l);
	}
	
	public boolean addLead(Lead l) {
		if(leads.indexOf((Object) l)>-1) return false;
		leads.add(l);
		return true;
	}
	
	public void addMeeting(Meeting m) {
		meetings.add(m);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass()!=SalesRep.class) return false;
		return (((SalesRep) o).name.equals(this.name) && ((SalesRep) o).getSalesRepID()==this.salesRepID);
	}
}
