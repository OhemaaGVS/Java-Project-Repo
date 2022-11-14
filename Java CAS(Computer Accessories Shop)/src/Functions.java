import java.io.BufferedWriter;
import java.util.HashMap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Functions {
	private HashMap<String, System_User> System_User_map = new HashMap<String, System_User>();// maps the username to
	// each user
	private HashMap<Integer, Stock> Stock_map = new HashMap<Integer, Stock>();// maps each barcode to the item

	private Mouse_Type mouse_type = null;// will store the type of mouse
	private Keyboard_Layout keyboard_layout = null;// will store the keyboard layout
	private Keyboard_Type keyboard_type = null;// will store the keyboard type
	private ArrayList<Stock> stock_list = new ArrayList<Stock>();// will store the list of stock
	private ArrayList<Stock> stock_filtered_by_brand = new ArrayList<Stock>();// will store stock based on brand
	// stores stock based on number of mouse // buttons
	private ArrayList<Stock> stock_filtered_by_buttons = new ArrayList<Stock>();

	public ArrayList<System_User> Read_User_Accounts(String fileName) {
		// this function reads the usernames from the text file and creates system users
		ArrayList<System_User> user_list = new ArrayList<System_User>();// creating a new list
		Scanner fileScanner = null;
		System_User_map.clear();
		try {
			File inputFile = new File(fileName);
			fileScanner = new Scanner(inputFile);
			Customer customer = null;// preparing the objects to be used, initiating the objects
			Admin admin = null;
			User_Address address = null;
			while (fileScanner.hasNextLine()) {// if there is a line to be read
				String[] Details = fileScanner.nextLine().split(",");// split the line
				if (Details[6].trim().equals("customer")) {// if the user is a customer
					address = new User_Address(Integer.parseInt(Details[3].trim()), Details[4].trim(),
							Details[5].trim());// create an address for the customer
					customer = new Customer(Integer.parseInt(Details[0].trim()), Details[1].trim(), Details[2].trim(),
							address, Details[6].trim());// create a new customer
					user_list.add(customer);// add the customer to the user list
					System_User_map.put(Details[1].trim(), customer);// add the user and their username into the map
				}
				if (Details[6].trim().equals("admin")) {// if they are an admin
					address = new User_Address(Integer.parseInt(Details[3].trim()), Details[4].trim(),
							Details[5].trim());// create an address for the admin
					admin = new Admin(Integer.parseInt(Details[0].trim()), Details[1].trim(), Details[2].trim(),
							address, Details[6].trim());// create an admin
					user_list.add(admin);// add the admin to the list
					System_User_map.put(Details[1].trim(), admin);// add the admin and their username to the map
				}
			}
			fileScanner.close();// close the scanner
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();// print this error if an error occurs
		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
		return (user_list);// return the list of users
	}

	private Keyboard_Type determine_keyboard_type(String type) {
		// this function determines the type of keyboard
		if (type.equals("standard")) {// of the keyboard is a standard
			keyboard_type = Keyboard_Type.STANDARD;// set the keyboard type to standard
		} else if (type.equals("gaming")) {
			keyboard_type = Keyboard_Type.GAMING;// set the keyboard type to gaming
		} else {
			keyboard_type = Keyboard_Type.FLEXIBLE;// set they keyboard type to flexible
		}
		return keyboard_type;// return the keyboard type
	}

	private Keyboard_Layout determine_keyboard_layout(String layout) {
		// this function determines the layout of the keyboard
		if (layout.equals("UK")) {
			keyboard_layout = Keyboard_Layout.UK;// set the keyboard layout to UK
		} else {
			keyboard_layout = Keyboard_Layout.US;// set the keyboard layout to US
		}

		return keyboard_layout;// return the layout
	}

	private Mouse_Type determine_mouse_type(String type) {
		// this function determines the type of mouse
		if (type.equals("gaming")) {
			mouse_type = Mouse_Type.GAMING;// set the mouse type to gaming
		} else {
			mouse_type = Mouse_Type.STANDARD;// set the mouse type to standard
		}
		return mouse_type;// return the type of the mouse
	}

	public ArrayList<Stock> Read_Stock(String fileName) {
// this function reads the data in the stock file to create stock objects
		Scanner fileScanner = null;
		stock_list.clear();
		Stock_map.clear();// clearing both the list and map so new data can be entered
		try {
			File inputFile = new File(fileName);
			fileScanner = new Scanner(inputFile);
			Keyboard keyboard = null;// Initialising the keyboard and mouse
			Mouse mouse = null;
			while (fileScanner.hasNextLine()) {// if there are lines to read
				String[] Details = fileScanner.nextLine().split(",");// split the line
				if (Details[1].trim().equals("keyboard")) {// of the item is a keyboard
					Keyboard_Type keyboard_type = determine_keyboard_type(Details[2].trim());
					// checking the keyboard type
					Keyboard_Layout keyboard_layout = determine_keyboard_layout(Details[9].trim());
					// checking the keyboard layout
					keyboard = new Keyboard(Integer.parseInt(Details[0].trim()), Stock_Type.KEYBOARD, Details[3].trim(),
							Details[4].trim(), Details[5].trim(), Integer.parseInt(Details[6].trim()),
							Double.parseDouble(Details[7].trim()), Double.parseDouble(Details[8].trim()), keyboard_type,
							keyboard_layout);
					// creating a keyboard
					stock_list.add(keyboard);// adding the keyboard to the stock list
					Stock_map.put(Integer.parseInt(Details[0].trim()), keyboard);
					// adding the stock with its barcode into the map
				}
				if (Details[1].trim().equals("mouse")) {// if the item is a mouse
					Mouse_Type mouse_type = determine_mouse_type(Details[2].trim());
					// determine what type of mouse it is
					mouse = new Mouse(Integer.parseInt(Details[0].trim()), Stock_Type.MOUSE, Details[3].trim(),
							Details[4].trim(), Details[5].trim(), Integer.parseInt(Details[6].trim()),
							Double.parseDouble(Details[7].trim()), Double.parseDouble(Details[8].trim()), mouse_type,
							Integer.parseInt(Details[9].trim()));
					// create the mouse
					stock_list.add(mouse);// add the mouse to the stock list
					Stock_map.put(Integer.parseInt(Details[0].trim()), mouse);
					// add the mouse and its barcode to the map
				}
			}
			fileScanner.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
		return (stock_list);// returning the list of stock
	}

	public boolean Check_Existing_Barcodes(int barcode) {
		// this function checks if the barcode already exists in the system
		boolean product_exists = false;
		if (Stock_map.containsKey(barcode) == true) {
			product_exists = true;// if the barcode exists, set this bool to true
		}
		return product_exists;// return the bool
	}

	public ArrayList<Stock> Search_Item_By_Brand(String brand_name) {
		// this function returns a list of stock that have the brand name specified
		Read_Stock("Stock.txt");// reading the stock file to make sure the latest data is in the list
		stock_filtered_by_brand.clear();// clear the old contents of the list
		for (Stock item : stock_list) {

			if (item.getBrand().equalsIgnoreCase(brand_name)) {
				stock_filtered_by_brand.add(item);
				// the brand name of an item is equal to the one searched for, add it to the
				// list
			}
		}
		return stock_filtered_by_brand;// return the list
	}

	public ArrayList<Stock> Search_Item_By_Buttons(int number) {
		// this function returns a list of stock that have the mouse buttons specified
		Read_Stock("Stock.txt");// reading the stock file to make sure the latest data is in the list
		stock_filtered_by_buttons.clear();// clear the old contents of the list
		for (Stock item : stock_list) {

			if (item instanceof Mouse) {
				if (((Mouse) item).getNumberOfButtons() == number)
					stock_filtered_by_buttons.add(item);
				// if the mouse has that number of buttons then add it to the list
			}
		}
		return stock_filtered_by_buttons;// return the list

	}

	public void Write_To_Stocks_File(String fileName) {
		// this function writes the stock data back into the file
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName, false));
			// false here means that we don't want to append data to the content of the
			// file.
			// If we need to append, then we should use true.
			// When we don't pass the second parameter (false/true), by default it would be
			// false.
			for (Stock item : stock_list) {

				if (item instanceof Keyboard) {
					bw.write(((Keyboard) item).toString() + "\n");// write the keyboard to the file
				} else {

					bw.write(((Mouse) item).toString() + "\n");// write the mouse to the file
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public boolean Check_User_Type(String username) {
		// this function checks if the user is a customer or admin
		boolean is_admin = false;
		if (System_User_map.containsKey(username) == true) {
			if (System_User_map.get(username).getUserType().equals("admin")) {
				is_admin = true;// if the user is an admin, set this bool to true
			} else {
				is_admin = false;// else they are a customer so set the bool to false
			}
		}
		return is_admin;// return the bools value
	}

	public void Create_New_Mouse(int barcode, String mousetype, String brand, String colour, String connectivity,
			int quantity, double original_cost, double retail_price, int buttons) {
		// this function creates a new mouse
		mouse_type = determine_mouse_type(mousetype);// check the type of mouse
		Mouse mouse = new Mouse(barcode, Stock_Type.MOUSE, brand, colour, connectivity, quantity, original_cost,
				retail_price, mouse_type, buttons);// creating the mouse
		stock_list.add(mouse);// add the mouse to the stock list
	}

	public void Create_New_Keyboard(int barcode, String keyboardtype, String brand, String colour, String connectivity,
			int quantity, double original_cost, double retail_price, String keyboardloayout) {
		// this function creates a new keyboard
		Keyboard_Type keyboard_type = determine_keyboard_type(keyboardtype);// checking the keyboards type
		Keyboard_Layout keyboard_layout = determine_keyboard_layout(keyboardloayout);// check layout
		Keyboard keyboard = new Keyboard(barcode, Stock_Type.KEYBOARD, brand, colour, connectivity, quantity,
				original_cost, retail_price, keyboard_type, keyboard_layout);// create the keyboard
		stock_list.add(keyboard);// adding the new keyboard to the list
	}

	public Stock return_item(int barcode) {
		// this function returns an item that corresponds to a barcode
		Stock item = null;// Initialise the stock variable
		if (Stock_map.containsKey(barcode) == true) {
			item = Stock_map.get(barcode);// if the barcode is found then store the item
		}
		return item;// return the item
	}

	public Customer get_user(String username) {
		// this function gets the user that corresponds to the username
		Customer customer = null;
		if (System_User_map.containsKey(username) == true) {
// if the username is found,store the customer
			customer = (Customer) System_User_map.get(username);
		}
		return customer;// return the customer that matched the username
	}

	public void update_stocks_after_purchsase(HashMap<Integer, Integer> shopping) {
		// this function updates the stock level when stock is bought
		for (Stock stock : stock_list) {
			int original_quantity = stock.getQuantity();// original quantity of the stock
			int barcode = stock.getBarcode();// barcode of the item
			int amount_bought = 0;// stores the amount that was bought
			if (shopping.containsKey(barcode) == true) {
				for (HashMap.Entry<Integer, Integer> entry : shopping.entrySet()) {
					if (entry.getKey() == barcode) {// if the barcode matches in the customers shopping basket
						amount_bought = entry.getValue();// get the quantity that was bought
					}
				}
				int new_quantity = original_quantity - amount_bought;
				// the new stocks quantity is equal to the original - the amount bought
				stock.setQuantity(new_quantity);// set the stocks new quantity
			}
		}
	}
}
