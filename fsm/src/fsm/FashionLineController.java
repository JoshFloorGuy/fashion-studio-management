package fsm;
import java.util.Date;

/**
 * 
 * @author jmedlund
 *
 */


public class FashionLineController {
	
	/**
	 * 
	 * @param name
	 * @param launch
	 * @return success or failure
	 */
	
	public static boolean addLine(String name, Date launch){
		FashionLine l = new FashionLine(name, launch);
		
		if(DatabaseSupport.exists(l))return false;
		
		DatabaseSupport.getLines().add(l);
		
		return true;
	}
	
}
