package MenuItems;
/**
 * Interface for the 
 * @author joshf
 *
 */
public interface iMenuItem {
	/**
	 * Returns the display name of this menu item
	 * @return display name
	 */
	public String getName();
	/**
	 * Executes the command in this menu item
	 */
	public void execute();
	/**
	 * Returns if this item should be numbered or not.
	 * Numbered items can be selected by users, if an
	 * item is not numbered, it can not be selected.
	 * @return if the item is numbered or not
	 */
	public boolean isNumbered();
	/**
	 * Returns if this item can be deleted from a menu.
	 * @return true or false
	 */
	public boolean deletable();
}