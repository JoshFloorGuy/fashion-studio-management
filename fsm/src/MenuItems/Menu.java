package MenuItems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Menu implements iMenuItem {
	private boolean quitMenu;
	private ArrayList<iMenuItem> items;
	private ArrayList<iMenuItem> selectable;
	private String errorMessage;
	private String name;
	
	public Menu(ArrayList<iMenuItem> i, String n) {
		quitMenu = false;
		name = n;
		items = i;
		selectable = new ArrayList<iMenuItem>();
		errorMessage = "";
		items.add(new QuitItem(this));
		updateSelectable();
	}
	
	public Menu(String n) {
		this(new ArrayList<iMenuItem>(),n);
	}
	
	public Menu() {
		this("Main Menu");
	}
	
	public void displayMenu() {
		displayMenu(false);
	}
	
	public void displayMenu(boolean numberAll) {
		displayMenu(numberAll,numberAll);
	}
	
	/**
	 * Displays the menu
	 * @param numberAll Determines if all items should be numbered
	 * @param showTail Determines if a blank item should be shown at the end
	 */
	public void displayMenu(boolean numberAll, boolean showTail) {
		int n = 0;
		Iterator<iMenuItem> i = items.iterator();
		iMenuItem j;
		while(i.hasNext()) {
			j = i.next();
			if(j.isNumbered() || numberAll) {
				n++;
				System.out.println(n+". "+j.getName());
			} else {
				System.out.println(j.getName());
			}
		}
		if(showTail) {
			n++;
			System.out.println(n+". (blank)");
		}
		if(errorMessage.length()>0) {
			System.out.println("\nError: "+errorMessage);
			errorMessage = "";
		}
	}
	
	/**
	 * Starts the menu, doesn't stop until "Quit" is selected.
	 */
	public void execute() {
		quitMenu = false;
		while(!quitMenu) {
			displayMenu();
			selectable.get(InputController.promptInteger("Please select an item:",1,selectable.size())-1).execute();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
	
	public void quit() {
		quitMenu = true;
	}
	
	public boolean isQuit() {
		return quitMenu;
	}
	
	/**
	 * Updates the list of items that can be selected
	 */
	public void updateSelectable() {
		Iterator<iMenuItem> i = items.iterator();
		selectable.clear();
		while(i.hasNext()) {
			iMenuItem j = i.next();
			if(j.isNumbered()) selectable.add(j);
		}
	}
	
	public void addItem(iMenuItem i) {
		items.add(i);
		if(i.isNumbered()) updateSelectable();
	}
	
	public void addItem(int index, iMenuItem i) {
		items.add(index, i);;
		if(i.isNumbered()) updateSelectable();
	}
	
	public void swapItems(iMenuItem i, iMenuItem o) {
		int a = items.indexOf(i);
		int b = items.indexOf(o);
		items.set(a, o);
		items.set(b, i);
		updateSelectable();
	}
	
	public void deleteItem(iMenuItem i) {
		items.remove(i);
		updateSelectable();
	}
	
	/**
	 * @return A list of all deletable items in this list
	 */
	public ArrayList<iMenuItem> getAllDeletable() {
		ArrayList<iMenuItem> ret = new ArrayList<iMenuItem>();
		Iterator<iMenuItem> i = items.iterator();
		iMenuItem j;
		while(i.hasNext()) {
			j = i.next();
			if(j.deletable()) ret.add(j);
		}
		return ret;
	}
	
	public iMenuItem getItem(int index) {
		return items.get(index);
	}
	
	public int size() {
		return items.size();
	}
}
