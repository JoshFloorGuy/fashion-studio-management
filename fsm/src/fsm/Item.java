package fsm;

/**
 * 
 * @author jmedlund
 *
 */

public class Item {
	private String name, type;
	private FashionLine line;
	private double price;

	public Item(String name, String type, FashionLine line, double price) {
		this.name = name;
		this.type = type;
		this.line = line;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FashionLine getLine() {
		return line;
	}

	public void setLine(FashionLine line) {
		this.line = line;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * returns true if items are identical in all ways
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Item))
			return false;

		if (((Item) o).getName().equals(this.name) && ((Item) o).getType().equals(this.type)
				&& ((Item) o).getLine().equals(this.line) && ((Item) o).getPrice() == this.price)
			return true;

		return false;
	}

	/**
	 * returns with format: name + " in " + line
	 */
	@Override
	public String toString(){
		return name + " in " + line;
	}
}
