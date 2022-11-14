public class Keyboard extends Stock {
// this class is a child of the stock class
	private Keyboard_Layout layout;// this will store the layout of the keyboard
	private Keyboard_Type keyboard_type;// this will store the type of keyboard an item is

	public Keyboard(int barcode, Stock_Type device_type, String brand, String colour, String connectivity,
			int stock_quantity, double original_cost, double retail_price, Keyboard_Type keyboard_type,
			Keyboard_Layout layout) {
		// the constructor for the keyboard class
		super(barcode, device_type, brand, colour, connectivity, stock_quantity, original_cost, retail_price);
// passing parameters to the super class

		this.keyboard_type = keyboard_type;// initialising the keyboard type
		this.layout = layout;// initialising the keyboard layout
	}

	public Keyboard_Layout getLayout() {// this function returns the layout of the keyboard
		return this.layout;
	}

	public Keyboard_Type getKeyboardType() {// this function returns the type of the keyboard
		return this.keyboard_type;
	}

	public String toString() {
// this overrides the to string function, this returns the keyboard info so it can be written into the file
		String keyboard_data = getBarcode() + ", " + getStockType().toString().toLowerCase() + ", "
				+ getKeyboardType().toString().toLowerCase() + ", " + getBrand() + ", " + getColour() + ", "
				+ getConnectivity() + ", " + getQuantity() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", "
				+ getLayout();
		return keyboard_data;
	}
}