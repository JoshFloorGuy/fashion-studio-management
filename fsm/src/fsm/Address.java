package fsm;

public class Address {
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zip;

	public Address(String s1, String s2, String c, String s, String z, String co) {
		this.street1 = s1;
		this.street2 = s2;
		this.city = c;
		this.state = s;
		this.zip = z;
		this.country = co;
	}
	
	public Address(String s1, String c, String s, String z) {
		this.street1 = s1;
		this.street2 = "";
		this.city = c;
		this.state = s;
		this.zip = z;
		this.country = "USA";
	}
	
	public String toString() {
		if(street2.length()>0) return (street1+"\n"+city+", "+state+" "+zip+"\n"+country);
		return (street1+"\n"+street2+"\n"+city+", "+state+" "+zip+"\n"+country);
	}
}
