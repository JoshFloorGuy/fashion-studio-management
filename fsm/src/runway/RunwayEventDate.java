package runway;
/**
 * Container class for the date of a runwayEvent
 * @author ejshouse
 *
 */
public class RunwayEventDate {
	String date;
	boolean booked;
	/**
	 * 
	 * @param date
	 * @param booked
	 */
	public RunwayEventDate(String date, boolean booked){
		this.date = date;
		this.booked = booked;
	}


	public String getDate(){
		return date;
	}
	public boolean getBooked(){
		return booked;
	}
}