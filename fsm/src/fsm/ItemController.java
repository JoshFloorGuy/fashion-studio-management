package fsm;

/**
 * 
 * @author jmedlund
 *
 */

public class ItemController {
	
	/**
	 * 
	 * @param name
	 * @param type
	 * @param lineName
	 * @param price
	 * @return success or failure
	 */
	public static boolean addItem(String name, String type, String lineName, double price) {

		FashionLine line = null;

		for (FashionLine l : DatabaseSupport.getLines()) {
			if (l.getName().equals(lineName)) {
				line = l;
				break;
			}
		}

		Item i = new Item(name, type, line, price);
		
		if(DatabaseSupport.exists(i))return false;
		
		if (line == null) {
			return false;
		}

		DatabaseSupport.getItems().add(i);

		return true;
	}
}
