public class Mouse extends Stock {
//this  class is a child of the stock class
	private Mouse_Type mouse_type;// this will store the mouse type
	private int number_of_mouse_buttons;// this will store the number of buttons a mouse can have

	public Mouse(int barcode, Stock_Type device_type, String brand, String colour, String connectivity,
			int stock_quantity, double original_cost, double retail_price, Mouse_Type mouse_type,
			int number_of_mouse_buttons) {
		// the constructor of the mouse class
		super(barcode, device_type, brand, colour, connectivity, stock_quantity, original_cost, retail_price);
		// passing the parameters to the parent class which is stock
		this.mouse_type = mouse_type;// Initialising the mouse type
		this.number_of_mouse_buttons = number_of_mouse_buttons;// Initialising the mouse number of buttons
	}

	public Mouse_Type getMouseType() {
		// this function returns the type of mouse the item is
		return this.mouse_type;
	}

	public int getNumberOfButtons() {
// this function returns the number of buttons that the mouse has
		return this.number_of_mouse_buttons;
	}

	public String toString() {
		// this overrides the to string function, this is used so the keyboard data can
		// be written to the file in the correct format
		String mouse_data = getBarcode() + ", " + getStockType().toString().toLowerCase() + ", "
				+ getMouseType().toString().toLowerCase() + ", " + getBrand() + ", " + getColour() + ", "
				+ getConnectivity() + ", " + getQuantity() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", "
				+ getNumberOfButtons();
		return mouse_data;// returning the string
	}
}