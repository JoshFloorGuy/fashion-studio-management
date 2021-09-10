package fsm;
/**
 * Container class for the date of a photoshoot
 * @author ejshouse
 *
 */
public class PhotoshootDate {
	String date;
	boolean booked;
	/**
	 * 
	 * @param date
	 * @param booked
	 */
	public PhotoshootDate(String date, boolean booked){
		this.date = date;
		this.booked = booked;
	}
	public void printDate(){
		System.out.println(this.date);
		System.out.println(this.booked);
	}
}