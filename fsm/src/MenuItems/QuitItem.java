package MenuItems;

public class QuitItem implements iMenuItem {
	Menu m;
	
	public QuitItem(Menu m) {
		this.m = m;
	}
	
	public void execute() {
		m.quit();
	}
	
	public String getName() {
		return "Quit";
	}
	
	public boolean isNumbered() {
		return true;
	}
	
	public boolean deletable() {
		return false;
	}
}
