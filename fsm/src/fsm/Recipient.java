package fsm;

public class Recipient {
	private String name;
	private String email;
	private Address address;
	
	public Recipient(String n, String e, Address a) {
		this.name = n;
		this.email = e;
		this.address = a;
	}
	
	@Override
	public String toString() {
		return (name+"\n"+email+"\n"+address);
	}
	
	public String getName() {
		return name;
	}
}
