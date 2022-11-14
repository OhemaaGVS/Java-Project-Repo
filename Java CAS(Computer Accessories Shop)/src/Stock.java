public abstract class Stock {
//this class holds the attributes and operations that each stock in the system will have
	private int barcode;// this will store the items barcode
	private Stock_Type device_type;// this will store the device type of the item
	private String brand;// this will store the brand of the item
	private String colour;// this will store the colour of the item
	private String connectivity;// this will store the connectivity of the item
	private int stock_quantity;// this will store the quantity level of the stock
	private double original_cost;// this will store the original cost of the stock
	private double retail_price;// this will store the retail price of the item

	public Stock(int barcode, Stock_Type device_type, String brand, String colour, String connectivity,
			int stock_quantity, double original_cost, double retail_price) {
// this is the constructor for the stock class
		this.barcode = barcode;// Initialising the barcode
		this.device_type = device_type;// Initialising the device type
		this.brand = brand;// Initialising the brand
		this.colour = colour;// Initialising the colour
		this.connectivity = connectivity;// Initialising the connectivity
		this.stock_quantity = stock_quantity;// Initialising the stocks quantity
		this.original_cost = original_cost;// Initialising the original cost
		this.retail_price = retail_price;// Initialising the retail price
	}

	public int getBarcode() {
// this function returns the barcode
		return this.barcode;
	}

	public Stock_Type getStockType() {
		// this function returns the type of stock
		return this.device_type;
	}

	public String getBrand() {
		// this function returns the brand
		return this.brand;
	}

	public String getColour() {
		// this function returns the colour
		return this.colour;
	}

	public String getConnectivity() {
		// this function returns the connectivity
		return this.connectivity;
	}

	public int getQuantity() {
		// this function returns the quantity of the stock
		return this.stock_quantity;
	}

	public double getOriginalCost() {
		// this function returns the original cost
		return this.original_cost;
	}

	public double getRetailPrice() {
		// this function returns the retail price
		return this.retail_price;
	}

	public void setQuantity(int new_quantity) {
		// this sets the new quantity of the stock when stock has been bought
		this.stock_quantity = new_quantity;
	}
}
