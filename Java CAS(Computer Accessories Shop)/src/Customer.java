import java.util.HashMap;// importing hash map so it can be used

public class Customer extends System_User {
//this class is a child of the super class system user
	private HashMap<Integer, Integer> shopping_basket_map = new HashMap<Integer, Integer>();
	// this hash map will map the stock id to the quantity wanting to be bought by
	// the user, this will be used as the shopping basket
	private HashMap<Integer, Stock> barcode_to_item_map = new HashMap<Integer, Stock>();
	// this hash map will map each stock to its barcode which will be used to aid
	// the use of the shopping basket

	public Customer(int ID, String username, String name, User_Address address, String user_type) {
		// the constructor of the customer class
		super(ID, username, name, address, user_type);// passing in the parameters to the parent class
	}

	public String toString() { // overrides the to string method
		return "customer name: " + getName() + getAddress().toString();// returns the customers name and their address
	}

	public void clear_shopping_basket() {// this function is used to clear the shopping basket and the helper hash map
		this.shopping_basket_map.clear();// clearing the customers shopping basket
		this.barcode_to_item_map.clear();// clearing the map from barcode to item
	}

	public void add_item_to_basket(int barcode, int quantity, Stock item) {
		// this function is used to add the item that the user wants to buy to their
		// shopping basket
		if (shopping_basket_map.containsKey(barcode) == true)// if the item is already in the users shopping basket
		{
			for (HashMap.Entry<Integer, Integer> entry : shopping_basket_map.entrySet()) {
				// for each item in their shopping basket
				int current_quantity = entry.getValue();// get the current quantity in their basket
				shopping_basket_map.put(barcode, quantity + current_quantity);// add the new quantity to the basket
			}
		} else {// if it wasnt already in the basket
			shopping_basket_map.put(barcode, quantity);// add the item's barcode and its quantity
			barcode_to_item_map.put(barcode, item);// add the item and its barcode
		}
	}

	public HashMap<Integer, Integer> get_Shopping_Basket() { // this function returns the customers shopping basket
		return shopping_basket_map;
	}

	public double get_total_payment() { // this function is used to calculate and return the total price for all the
										// items in the customers shopping basket
		double total = 0;
		for (HashMap.Entry<Integer, Integer> entry : shopping_basket_map.entrySet()) {
			// for each item in the basket
			Stock item = null;
			if (barcode_to_item_map.containsKey(entry.getKey()) == true)// if the barcode is in the barcode to item map
			{
				item = barcode_to_item_map.get(entry.getKey());// storing the stock that corresponds to that barcode
			}
			double cost = item.getRetailPrice();// storing the retail price of that item
			int quantity = entry.getValue();// storing the quantity of the item thats being bought
			double total_cost_of_item = quantity * cost;// total of each item
			total = total + total_cost_of_item;// adding to the total
		}
		return total;// return the total
	}

}