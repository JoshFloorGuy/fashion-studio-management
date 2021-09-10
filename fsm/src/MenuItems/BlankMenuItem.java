package MenuItems;

public class BlankMenuItem implements iMenuItem {
	private String name;
	private boolean isNumbered;
	private boolean deletable;
	
	public BlankMenuItem (String n, boolean in, boolean d) {
		name = n;
		deletable = d;
		isNumbered = in;
	}
	
	public BlankMenuItem (String n, boolean in) {
		this(n,in,true);
	}
	
	public void execute() {}
	
	public boolean isNumbered() {
		return isNumbered;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean deletable() {
		return deletable;
	}
}