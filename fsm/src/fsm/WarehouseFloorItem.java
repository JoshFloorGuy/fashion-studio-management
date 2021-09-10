package fsm;

public class WarehouseFloorItem {
	private String location;
	private Item item;
	private int quantity;

	/**
	 * Represents a location on the Warehouse floor. This will have an
	 * item, a location, and a quantity of said item associated with it.
	 * @param l Location on the floor (Could be any format)
	 * @param i Item that is in this location
	 * @param q Quantity of Item at this spot
	 */
	public WarehouseFloorItem(String l, Item i, int q) {
		this.location = l;
		this.item = i;
		this.quantity = q;
	}

	public String getName() {
		return item.getName();
	}

	public String getFloorLocation() {
		return location;
	}

	@Override
	public String toString() {
		return ("Item name: " + item.getName() + "\nFloor location: " + location + "\nQuantity: " + quantity);
	}

	public void setQuantity(int q) {
		quantity = q;
	}

	public int getQuantity() {
		return quantity;
	}
}
