/**
 * 
 */
package hr;

/**
 * @author jmedl
 *
 */
public class Employee {
	private String name;
	private String title;
	private String location;
	private double payRate;
	
	Employee(String name, String title, String location, double payRate){
		this.name = name;
		this.title = title;
		this.location = location;
		this.payRate = payRate;
	}
	
	@Override
	public String toString(){
		return name + " the " + title + " at " + location + " salary " + payRate;
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getTitle() {
		return title;
	}
	
	void setTitle(String title) {
		this.title = title;
	}
	
	String getLocation() {
		return location;
	}
	
	void setLocation(String location) {
		this.location = location;
	}
	
	double getPayRate() {
		return payRate;
	}
	
	void setPayRate(double payRate) {
		this.payRate = payRate;
	}
	
}
