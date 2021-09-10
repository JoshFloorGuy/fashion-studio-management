package MenuItems;

import java.util.ArrayList;
import java.util.Iterator;

public class DeleteMenuItem implements iMenuItem {
	private Menu m;
	
	public DeleteMenuItem(Menu m) {
		this.m = m;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public String getName() {
		return "Delete Item";
	}
	
	/**
	 * Deletes an item that the user specifies.
	 * User can specify 0 to not delete an item.
	 */
	public void execute() {
		ArrayList<iMenuItem> d = m.getAllDeletable();
		Iterator<iMenuItem> l = d.iterator();
		int n = 0;
		while(l.hasNext()) {
			n++;
			System.out.println(n+". "+l.next().getName());
		}
		int i = InputController.promptInteger("Please enter the index of the item you would like to delete (0 to cancel)",0,d.size());
		if(i!=0) {
			iMenuItem k = d.get(i-1);
			m.deleteItem(k);
		}
	}
}
