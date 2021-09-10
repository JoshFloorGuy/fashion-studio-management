package fsm.sales;

/**
 * This represents a contact, complete with name and
 * phone number.
 * @author joshf
 *
 */
public class Contact {
	public String name;
	public String phoneNumber;
	
	public Contact(String n, String p) {
		name = n;
		phoneNumber = p;
	}
	
	@Override
	public String toString() {
		return name + ": " + phoneNumber;
	}
}
