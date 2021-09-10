package fsm.sales;

import fsm.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This represents a meeting that a user has set up with 
 * a client. It contains the lead that it originated from,
 * and the list of lines that will be discussed, as well as
 * an agreement, once one is created.
 * @author joshf
 *
 */
public class Meeting {
	private Lead lead;
	private String dateOfMeeting;
	private ArrayList<FashionLine> lines;
	private boolean isDone;
	private Agreement agreement;
	
	public Meeting(Lead l, String d, ArrayList<FashionLine> f) {
		lead = l;
		dateOfMeeting = d;
		lines = f;
		isDone = false;
		agreement = null;
	}
	
	public boolean isMeetingDone() {
		return isDone;
	}
	
	public Agreement getAgreement() {
		return agreement;
	}
	
	public void setAgreement(Agreement a) {
		agreement = a;
	}
	
	public ArrayList<FashionLine> getLines() {
		return lines;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return lead.getCompany() + " @ " +dateOfMeeting;
	}
	
	/**
	 * This loops through all the lines, and presents each item to the client
	 * On end, the meeting will be set to done
	 */
	public void startMeeting() {
		Iterator<FashionLine> fl = lines.iterator();
		Scanner n = new Scanner(System.in);
		while(fl.hasNext()) {
			FashionLine l = fl.next();
			ArrayList<Item> items = DatabaseSupport.getItemsByLine(l);
			System.out.println(l);
			Iterator<Item> i = items.iterator();
			while(i.hasNext()) {
				System.out.println("");
				System.out.println(i.next());
				System.out.println("Press enter to continue");
				n.nextLine();
			}
		}
		System.out.println("\nThis concludes the presentation\nPress Enter to continue");
		n.nextLine();
		isDone = true;
	}
	
}
