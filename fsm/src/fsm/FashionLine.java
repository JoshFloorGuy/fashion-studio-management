package fsm;

import java.util.Date;

/**
 * 
 * @author jmedlund
 *
 */

public class FashionLine {
	private String name;
	private Date launchDate;
	
	public FashionLine(String name, Date launchDate){
		this.name = name;
		this.launchDate = launchDate;
	}
	
	public Date getLaunchDate(){
		return this.launchDate;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean equals(Object o){
		if(o == this)return true;
		
		if(!(o instanceof FashionLine)) return false;
		
		if(this.name.equals(((FashionLine)o).getName()))return true;
		
		return false;
		
	}
	
	/**
	 * returns with format: name + " starting on " + launchDate
	 */
	@Override
	public String toString(){
		return name + " starting on " + launchDate;
	}
}
