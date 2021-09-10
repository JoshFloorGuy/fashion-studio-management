package runway;
/**
 * container class that defines a team member for a runway event
 * @author Erik
 *
 */
public class RunwayEventTeamMember {
	String name;
	double salary;
	/**
	 * creates an instance of a team member with given name and salary
	 * @param name
	 * @param salary
	 */
	public RunwayEventTeamMember(String name, double salary)
	{
		this.name= name;
		this.salary=salary;
	}
	/**
	 * default constructor
	 */
	public RunwayEventTeamMember() {
		this.name= null;
		this.salary = 0.0;
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setSalary(double salary) {
		this.salary=salary;
	}
}
