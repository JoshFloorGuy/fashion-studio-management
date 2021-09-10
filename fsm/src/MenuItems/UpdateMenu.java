package MenuItems;

public class UpdateMenu extends Menu {
	
	/**
	 * Creates a menu with methods to manage items
	 * within a parent menu
	 * @param m menu that can be edited
	 */
	public UpdateMenu(Menu m) {
		super("Edit Menu");
		super.addItem(0, new BlankMenuItem("--- Update "+m.getName()+" ---",false));
		super.addItem(1, new AddMenuItem(m));
		super.addItem(2, new AddMenuDivide(m));
		super.addItem(3, new MoveMenuItem(m));
		super.addItem(4, new SwapMenuItem(m));
		super.addItem(5, new DeleteMenuItem(m));
		super.addItem(new MenuDivide());
	}
	
	@Override
	public boolean deletable() {
		return false;
	}
}
