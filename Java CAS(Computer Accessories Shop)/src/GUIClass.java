import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.Color;

// importing different classes^
public class GUIClass extends JFrame {
//creating attributes for the GUI class privately and enabling them all to be accessed throughout this class
	private JPanel contentPane;
	private JTable tblUsernames;
	private DefaultTableModel dtmUsernames;// default models for each of the jtables
	private DefaultTableModel dtmViewAllItems;
	private DefaultTableModel dtmShoppingItems;
	private DefaultTableModel dtmShoppingBasket;
	private ArrayList<System_User> user_list;// will store the list of users
	private ArrayList<Stock> stock_list;// will store the list of stock
	private String chosen_username;// stores the user name selected
	private Customer customer;// will store the customer created
	private String selected_item;// stores the item selected from the table when the customer is shopping
	private Functions fn = new Functions();// instance of the functions class
	private boolean is_user_admin;// whether the user is an admin or not
	private JPanel LoginTab;// different tabs
	private JPanel ViewAllItemsTab;
	private JPanel AddMouseTab;
	private JPanel ShoppingBasketTab;
	private JPanel AddKeyboardTab;
	private JPanel ShopForItemsTab;
	private JTabbedPane tabbedPane;
	private JButton btnLogOut;// button used to log out
	private JTable tblViewAllItems;// table that shows all items
	private JScrollPane AllItemscrollPane_1;
	private JButton btnLogIn;// button used to log in
	private JTextField txtBarcode;// text fields for the new item being created
	private JTextField txtBrand;
	private JTextField txtColour;
	private JTextField txtOriginal_Cost;
	private JTextField txtRetail_Price;
	private JButton btnAddMouse;// button that creates a new mouse
	private JComboBox cmbMouseType;// the combo box that will store the mouse type
	private JComboBox cmbConnectivity;// combo box that stores the connectivity
	private JSpinner spinnerQuantity;
	private JSpinner spinnerMouseButtons;
	private JTextField txtKeyboardBarcode;// text boxes for adding a new keyboard
	private JTextField txtKeyboardBrand;
	private JTextField txtKeyboardColour;
	private JTextField txtKeyboardOriginalCost;
	private JTextField txtKeyboardRetailPrice;
	private JComboBox cmbKeyboardType;// contains the keyboard types
	private JComboBox cmbKeyboardConnectivty;
	private JSpinner spinnerKeyboardQuantity;
	private JButton btnAddKeyboard;// button used to add a keyboard
	private JComboBox cmbKeyboardLayout;
	private final String[] connections = { "wired", "wireless" };// values that will be put in the combo box
	private JTable tblShoppingItems;
	private JTextField txtSearchBrand;
	private JSpinner spinnerQuantityToBuy;
	private JButton btnAddToShoppingBasket;
	private JButton btnSearchBrand;
	private JSpinner spinnerSearchButtonNumber;
	private JButton btnSearchMouseButton;
	private ArrayList<Stock> stock_filtered_by_brand = new ArrayList<Stock>();
	// contains the list of items that have a specific brand name
	private ArrayList<Stock> stock_filtered_by_buttons = new ArrayList<Stock>();
	// contains the list of items that have a specific number of buttons
	private JButton btnClearShoppingBasket;
	private JTable tblShoppingBasket;// table that shows the shopping basket
	private JScrollPane shoppingBasketscrollPane_3;
	private JButton btnPaypal;
	private JButton btnPayCreditCard;
	private JButton btnViewAllShoppingItems;
	private JLabel MouseBarcodeMessageLabel;// labels used to convey messages
	private JLabel MouseColourMessageLabel;
	private JLabel MouseOriginalCostMessageLabel;
	private JLabel MouseRetailPriceMessageLabel;
	private JLabel KeyboardBarcodeMessageLabel;
	private JLabel KeyboardColourMessageLabel;
	private JLabel KeyboardOriginalCostMessageLabel;
	private JLabel KeyboardRetailPriceMessageLabel;
	private JLabel KeyboardBrandMessageLabel;
	private JLabel MouseBrandMessageLabel;
	private JLabel basketLabel;
	private JLabel lblTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// when the program is first ran
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIClass frame = new GUIClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIClass() {
		// the constructor for the GUIClass
		// creating the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(127, 255, 212));
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// when a new tab is pressed on
				int selectedIndex = tabbedPane.getSelectedIndex();// store the tab index
				String tab_name = tabbedPane.getTitleAt(selectedIndex).toString();
				determine_tab(tab_name);// call the function that determines what happens when a tab is loaded
			}
		});
		tabbedPane.setBounds(10, 10, 1021, 519);
		contentPane.add(tabbedPane);
		LoginTab = new JPanel();// creating the log in tab
		LoginTab.setBackground(new Color(127, 255, 212));
		tabbedPane.addTab("Login", null, LoginTab, null);
		LoginTab.setLayout(null);
		JScrollPane usernamescrollPane = new JScrollPane();
		usernamescrollPane.setBounds(212, 21, 561, 228);
		LoginTab.add(usernamescrollPane);

		tblUsernames = new JTable();// table that will display the usernames
		tblUsernames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernamescrollPane.setViewportView(tblUsernames);
		tblUsernames.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Get_Selected_Username();
// when a row on the table is selected, the above function is called
			}
		});
		tblUsernames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// only single select is allowed
		usernamescrollPane.setViewportView(tblUsernames);
		// Default headings for the table
		dtmUsernames = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				// making sure cells in the jtable can not be edited
				return false;
			}
		};
		dtmUsernames.setColumnIdentifiers(new Object[] { "Usernames" });// column header of the table
		tblUsernames.setModel(dtmUsernames);
		btnLogIn = new JButton("Login");// creating the log in button
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();// when the log in button is pressed, the log in function is called
			}
		});
		btnLogIn.setBounds(212, 259, 126, 40);
		LoginTab.add(btnLogIn);
		btnLogOut = new JButton("Log Out");// creating the log out button
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log_Out();// when the log out button is pressed, this function is called
			}
		});
		btnLogOut.setBounds(212, 333, 126, 40);
		LoginTab.add(btnLogOut);
		ShoppingBasketTab = new JPanel();// creating the shopping basket tab
		ShoppingBasketTab.setBackground(new Color(127, 255, 212));
		tabbedPane.addTab("Shopping Basket Tab", null, ShoppingBasketTab, null);
		ShoppingBasketTab.setLayout(null);
		shoppingBasketscrollPane_3 = new JScrollPane();
		shoppingBasketscrollPane_3.setBounds(10, 10, 984, 284);
		ShoppingBasketTab.add(shoppingBasketscrollPane_3);
		tblShoppingBasket = new JTable();// table that will show the items in the users shopping basket
		dtmShoppingBasket = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				// making sure the table cant be edited
				return false;
			}
		};
		dtmShoppingBasket.setColumnIdentifiers(new Object[] { "Barcode", "Name", "Device Type", "Brand", "Colour",
				"Connectvity", "Retail Price", "Layout", "Buttons", "Quantity being bought" });
		// setting the tables columns
		tblShoppingBasket.setModel(dtmShoppingBasket);// setting the tables default model
		tblShoppingBasket.getColumnModel().getColumn(0).setPreferredWidth(30);// setting the widths of the columns
		tblShoppingBasket.getColumnModel().getColumn(1).setPreferredWidth(65);
		tblShoppingBasket.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblShoppingBasket.getColumnModel().getColumn(9).setPreferredWidth(120);
		tblShoppingBasket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// can only select one row
		shoppingBasketscrollPane_3.setViewportView(tblShoppingBasket);
		btnClearShoppingBasket = new JButton("Clear Shopping Basket");// this button clears the shopping basket
		btnClearShoppingBasket.setBounds(23, 377, 205, 35);
		ShoppingBasketTab.add(btnClearShoppingBasket);
		btnPaypal = new JButton("Pay by Paypal");// button used to pay by paypal
		btnPaypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the button is clicked , this function is called
				Pay_by_Paypal();
			}
		});
		btnPaypal.setBounds(347, 377, 175, 35);
		ShoppingBasketTab.add(btnPaypal);
		btnPayCreditCard = new JButton("Pay by Credit Card");// button used to pay by card
		btnPayCreditCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the button is clicked,this function is called
				Pay_by_Credit_Card();
			}
		});
		btnPayCreditCard.setBounds(612, 377, 193, 35);
		ShoppingBasketTab.add(btnPayCreditCard);
		basketLabel = new JLabel("");// label that shows whose basket it is
		basketLabel.setFont(new Font("Segoe UI Semilight", Font.BOLD, 16));
		basketLabel.setBounds(10, 315, 312, 34);
		ShoppingBasketTab.add(basketLabel);
		lblTotal = new JLabel("");// this label will show the current total
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(347, 322, 260, 27);
		ShoppingBasketTab.add(lblTotal);
		btnClearShoppingBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear_Basket();
//when the button for clearing the basket is pressed, this function is called
			}
		});
		ViewAllItemsTab = new JPanel();// creating the tab that shows all the stock for the admin
		ViewAllItemsTab.setBackground(new Color(127, 255, 212));
		tabbedPane.addTab("View All Items", null, ViewAllItemsTab, null);
		ViewAllItemsTab.setLayout(null);
		AllItemscrollPane_1 = new JScrollPane();
		AllItemscrollPane_1.setBounds(10, 20, 970, 462);
		ViewAllItemsTab.add(AllItemscrollPane_1);
		tblViewAllItems = new JTable();
		AllItemscrollPane_1.setViewportView(tblViewAllItems);
		tblViewAllItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// single selection
		dtmViewAllItems = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				// make sure that the cells in the jtable cant be typed into
				return false;
			}
		};
		dtmViewAllItems.setColumnIdentifiers(new Object[] { "Barcode", "Name", "Device Type", "Brand", "Colour",
				"Connectvity", "Quantity", "Original Cost", "Retail Price", "Layout", "Buttons" });
		// set the column headers for the table
		tblViewAllItems.setModel(dtmViewAllItems);// set the table to the default model
		tblViewAllItems.getColumnModel().getColumn(0).setPreferredWidth(45);// set the widths of some columns
		tblViewAllItems.getColumnModel().getColumn(1).setPreferredWidth(65);
		tblViewAllItems.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblViewAllItems.getColumnModel().getColumn(9).setPreferredWidth(100);

		AddMouseTab = new JPanel();// add the tab for adding new mouses
		AddMouseTab.setBackground(new Color(127, 255, 212));
		tabbedPane.addTab("Add New Mouse", null, AddMouseTab, null);
		AddMouseTab.setLayout(null);

		txtBarcode = new JTextField();// barcode field for the mouse
		txtBarcode.setBounds(121, 10, 96, 19);
		AddMouseTab.add(txtBarcode);
		txtBarcode.setColumns(10);

		cmbMouseType = new JComboBox(Mouse_Type.values());// type field for the mouse
		cmbMouseType.setBounds(121, 63, 96, 21);
		AddMouseTab.add(cmbMouseType);

		txtBrand = new JTextField();// brand field for the mouse
		txtBrand.setBounds(121, 123, 96, 19);
		AddMouseTab.add(txtBrand);
		txtBrand.setColumns(10);

		txtColour = new JTextField();// colour field for the mouse
		txtColour.setBounds(121, 167, 96, 19);
		AddMouseTab.add(txtColour);
		txtColour.setColumns(10);
		cmbConnectivity = new JComboBox(connections);// connectivity field for the mouse

		cmbConnectivity.setBounds(121, 208, 96, 21);
		AddMouseTab.add(cmbConnectivity);

		spinnerQuantity = new JSpinner();// quantity field for the mouse
		spinnerQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQuantity.setEditor(new JSpinner.DefaultEditor(spinnerQuantity));
		spinnerQuantity.setBounds(121, 264, 96, 19);
		AddMouseTab.add(spinnerQuantity);

		txtOriginal_Cost = new JTextField();// cost field for the mouse
		txtOriginal_Cost.setBounds(121, 306, 96, 19);
		AddMouseTab.add(txtOriginal_Cost);
		txtOriginal_Cost.setColumns(10);

		txtRetail_Price = new JTextField();// retail price field for the mouse
		txtRetail_Price.setBounds(121, 350, 96, 19);
		AddMouseTab.add(txtRetail_Price);
		txtRetail_Price.setColumns(10);

		spinnerMouseButtons = new JSpinner();// button number field for the mouse
		spinnerMouseButtons.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		// setting the min and default value
		//
		spinnerMouseButtons.setEditor(new JSpinner.DefaultEditor(spinnerMouseButtons));
		spinnerMouseButtons.setBounds(121, 399, 96, 20);
		AddMouseTab.add(spinnerMouseButtons);

		JLabel lblMouseBarcode = new JLabel("Barcode");// label for barcode for the mouse
		lblMouseBarcode.setBounds(23, 11, 66, 16);
		AddMouseTab.add(lblMouseBarcode);

		JLabel lblMouseType = new JLabel("Mouse Type");// label for mouse type for the mouse
		lblMouseType.setBounds(23, 67, 88, 13);
		AddMouseTab.add(lblMouseType);

		JLabel lblMouseBrand = new JLabel("Brand");// label for brand for the mouse
		lblMouseBrand.setBounds(23, 126, 45, 13);
		AddMouseTab.add(lblMouseBrand);

		JLabel lblMouseColour = new JLabel("Colour");// label for colour for the mouse
		lblMouseColour.setBounds(23, 170, 45, 13);
		AddMouseTab.add(lblMouseColour);

		JLabel lblMouseConnectivity = new JLabel("Connectivity");// label for connectivity for the mouse
		lblMouseConnectivity.setBounds(23, 212, 88, 13);
		AddMouseTab.add(lblMouseConnectivity);

		JLabel lblMouseQuantity = new JLabel("Quantity");// label for quantity for the mouse
		lblMouseQuantity.setBounds(23, 267, 80, 13);
		AddMouseTab.add(lblMouseQuantity);

		JLabel lblMouseOriginalCost = new JLabel("Original Cost");// label for original cost for the mouse
		lblMouseOriginalCost.setBounds(23, 309, 88, 13);
		AddMouseTab.add(lblMouseOriginalCost);

		JLabel lblMouseRetailPrice = new JLabel("Retail Price");// label for retail price for the mouse
		lblMouseRetailPrice.setBounds(23, 353, 67, 13);
		AddMouseTab.add(lblMouseRetailPrice);

		JLabel lblMouseButtons = new JLabel("Buttons");// label for number of buttons for the mouse
		lblMouseButtons.setBounds(23, 402, 88, 13);
		AddMouseTab.add(lblMouseButtons);

		btnAddMouse = new JButton("Add Mouse");// button used to add a mouse
		btnAddMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the add mouse button is pressed this function is called
				Add_Mouse();
			}
		});
		btnAddMouse.setBounds(121, 437, 106, 34);
		AddMouseTab.add(btnAddMouse);

		MouseBarcodeMessageLabel = new JLabel("");// error messages labels:
		MouseBarcodeMessageLabel.setBounds(277, 13, 584, 13);
		AddMouseTab.add(MouseBarcodeMessageLabel);

		MouseColourMessageLabel = new JLabel("");
		MouseColourMessageLabel.setBounds(227, 170, 485, 13);
		AddMouseTab.add(MouseColourMessageLabel);

		MouseOriginalCostMessageLabel = new JLabel("");
		MouseOriginalCostMessageLabel.setBounds(253, 309, 365, 13);
		AddMouseTab.add(MouseOriginalCostMessageLabel);

		MouseRetailPriceMessageLabel = new JLabel("");
		MouseRetailPriceMessageLabel.setBounds(267, 353, 386, 13);
		AddMouseTab.add(MouseRetailPriceMessageLabel);

		MouseBrandMessageLabel = new JLabel("");
		MouseBrandMessageLabel.setBounds(267, 126, 315, 13);
		AddMouseTab.add(MouseBrandMessageLabel);

		AddKeyboardTab = new JPanel();// creating the keyboard tab
		AddKeyboardTab.setBackground(new Color(127, 255, 212));
		tabbedPane.addTab("Add New Keyboard", null, AddKeyboardTab, null);
		AddKeyboardTab.setLayout(null);

		txtKeyboardBarcode = new JTextField();// the barcode field for the keyboard
		txtKeyboardBarcode.setBounds(148, 23, 106, 19);
		AddKeyboardTab.add(txtKeyboardBarcode);
		txtKeyboardBarcode.setColumns(10);

		cmbKeyboardType = new JComboBox(Keyboard_Type.values());// the type field for the keyboard
		cmbKeyboardType.setBounds(148, 83, 111, 21);
		AddKeyboardTab.add(cmbKeyboardType);

		txtKeyboardBrand = new JTextField();// the brand field for the keyboard
		txtKeyboardBrand.setBounds(148, 131, 106, 19);
		AddKeyboardTab.add(txtKeyboardBrand);
		txtKeyboardBrand.setColumns(10);

		txtKeyboardColour = new JTextField();// the colour field for the keyboard
		txtKeyboardColour.setBounds(148, 190, 96, 19);
		AddKeyboardTab.add(txtKeyboardColour);
		txtKeyboardColour.setColumns(10);

		cmbKeyboardConnectivty = new JComboBox(connections);// the connectivity field for the keyboard
		cmbKeyboardConnectivty.setBounds(148, 238, 96, 21);
		AddKeyboardTab.add(cmbKeyboardConnectivty);

		spinnerKeyboardQuantity = new JSpinner();// the quantity field for the keyboard
		spinnerKeyboardQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		// setting defaults
		spinnerKeyboardQuantity.setEditor(new JSpinner.DefaultEditor(spinnerKeyboardQuantity));
		spinnerKeyboardQuantity.setBounds(148, 283, 96, 20);
		AddKeyboardTab.add(spinnerKeyboardQuantity);

		txtKeyboardOriginalCost = new JTextField();// the original cost field for the keyboard
		txtKeyboardOriginalCost.setBounds(148, 332, 96, 19);
		AddKeyboardTab.add(txtKeyboardOriginalCost);
		txtKeyboardOriginalCost.setColumns(10);

		txtKeyboardRetailPrice = new JTextField();// the retail price field for the keyboard
		txtKeyboardRetailPrice.setBounds(148, 372, 96, 19);
		AddKeyboardTab.add(txtKeyboardRetailPrice);
		txtKeyboardRetailPrice.setColumns(10);

		cmbKeyboardLayout = new JComboBox(Keyboard_Layout.values());
		cmbKeyboardLayout.setBounds(148, 415, 89, 21);
		AddKeyboardTab.add(cmbKeyboardLayout);

		JLabel lblKeyboardBarcode = new JLabel("Barcode");// barcode label for the keyboard
		lblKeyboardBarcode.setBounds(26, 24, 55, 16);
		AddKeyboardTab.add(lblKeyboardBarcode);

		JLabel lblKeyboardType = new JLabel("Keyboard Type");// keyboard type label for the keyboard
		lblKeyboardType.setBounds(26, 85, 89, 17);
		AddKeyboardTab.add(lblKeyboardType);

		JLabel lblKeyboardBrand = new JLabel("Brand");// brand label for the keyboard
		lblKeyboardBrand.setBounds(26, 134, 45, 13);
		AddKeyboardTab.add(lblKeyboardBrand);

		JLabel lblKeyboardColour = new JLabel("Colour");// colour label for the keyboard
		lblKeyboardColour.setBounds(26, 193, 45, 13);
		AddKeyboardTab.add(lblKeyboardColour);

		JLabel lblKeyboardConnectivity = new JLabel("Connectivity");// connectivity label for the keyboard
		lblKeyboardConnectivity.setBounds(26, 242, 72, 13);
		AddKeyboardTab.add(lblKeyboardConnectivity);

		JLabel lblKeyboardQuantity = new JLabel("Qunatity");// quantity label for the mouse
		lblKeyboardQuantity.setBounds(29, 286, 86, 13);
		AddKeyboardTab.add(lblKeyboardQuantity);

		JLabel lblKeyboardOriginalCost = new JLabel("Original Cost");// original cost label for the keyboard
		lblKeyboardOriginalCost.setBounds(26, 335, 112, 13);
		AddKeyboardTab.add(lblKeyboardOriginalCost);

		JLabel lblKeyboardRetailPrice = new JLabel("Retail Price");// retail price label for the keyboard
		lblKeyboardRetailPrice.setBounds(26, 375, 89, 13);
		AddKeyboardTab.add(lblKeyboardRetailPrice);

		JLabel lblKeyboardLayout = new JLabel("Keyboard Layout");// layout label for the keyboard
		lblKeyboardLayout.setBounds(26, 419, 112, 13);
		AddKeyboardTab.add(lblKeyboardLayout);

		btnAddKeyboard = new JButton("Add KeyBoard");// button for adding the keyboard
		btnAddKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the button is pressed this function is called
				Add_Keyboard();

			}
		});
		btnAddKeyboard.setBounds(148, 446, 143, 36);
		AddKeyboardTab.add(btnAddKeyboard);

		KeyboardBarcodeMessageLabel = new JLabel("");// message labels for validation on keyboards:
		KeyboardBarcodeMessageLabel.setBounds(340, 23, 353, 13);
		AddKeyboardTab.add(KeyboardBarcodeMessageLabel);

		KeyboardColourMessageLabel = new JLabel("");
		KeyboardColourMessageLabel.setBounds(254, 193, 413, 13);
		AddKeyboardTab.add(KeyboardColourMessageLabel);

		KeyboardOriginalCostMessageLabel = new JLabel("");
		KeyboardOriginalCostMessageLabel.setBounds(254, 335, 362, 13);
		AddKeyboardTab.add(KeyboardOriginalCostMessageLabel);

		KeyboardRetailPriceMessageLabel = new JLabel("");
		KeyboardRetailPriceMessageLabel.setBounds(254, 375, 362, 13);
		AddKeyboardTab.add(KeyboardRetailPriceMessageLabel);

		KeyboardBrandMessageLabel = new JLabel("");
		KeyboardBrandMessageLabel.setBounds(295, 134, 321, 13);
		AddKeyboardTab.add(KeyboardBrandMessageLabel);

		ShopForItemsTab = new JPanel();// adding the shop for items tab
		ShopForItemsTab.setBackground(new Color(127, 255, 212));
		tabbedPane.addTab("Shop For Items", null, ShopForItemsTab, null);
		ShopForItemsTab.setLayout(null);

		JScrollPane shoppingItemscrollPane_2 = new JScrollPane();
		shoppingItemscrollPane_2.setBounds(21, 20, 996, 180);
		ShopForItemsTab.add(shoppingItemscrollPane_2);

		tblShoppingItems = new JTable();// table that will display the items that can be bought
		tblShoppingItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Get_Selected_Item();
				// when an item is selected from the table then this function is called
			}
		});
		dtmShoppingItems = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				// making sure the table cant be typed into
				return false;
			}
		};
		dtmShoppingItems.setColumnIdentifiers(new Object[] { "Barcode", "Name", "Device Type", "Brand", "Colour",
				"Connectvity", "Quantity", "Retail Price", "Layout", "Buttons" });
		tblShoppingItems.setModel(dtmShoppingItems);// setting the column headers with its correct widths
		tblShoppingItems.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblShoppingItems.getColumnModel().getColumn(1).setPreferredWidth(65);
		tblShoppingItems.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblShoppingItems.getColumnModel().getColumn(9).setPreferredWidth(100);
		shoppingItemscrollPane_2.setViewportView(tblShoppingItems);
		tblShoppingItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// only allow one item to be selected at a time
		JLabel lblquantity = new JLabel("Quantity of item");// quantity to buy label
		lblquantity.setBounds(39, 247, 92, 17);
		ShopForItemsTab.add(lblquantity);

		spinnerQuantityToBuy = new JSpinner();// spinner for selecting how much stock is to be bought
		spinnerQuantityToBuy.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQuantityToBuy.setEditor(new JSpinner.DefaultEditor(spinnerQuantityToBuy));// setting the defaults
		spinnerQuantityToBuy.setBounds(39, 277, 107, 20);
		ShopForItemsTab.add(spinnerQuantityToBuy);

		btnAddToShoppingBasket = new JButton("Add to Shopping Basket ");// adding the add to basket button
		btnAddToShoppingBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when this button is clicked this function is called
				Add_To_Shopping_Basket();
			}
		});
		btnAddToShoppingBasket.setBounds(39, 323, 202, 21);
		ShopForItemsTab.add(btnAddToShoppingBasket);

		JLabel lblSearchBrand = new JLabel("Type in Brand Name");// label indicating where to type the brand name
		lblSearchBrand.setBounds(445, 249, 165, 13);
		ShopForItemsTab.add(lblSearchBrand);
		// label indicating where to select the button number
		JLabel lblSearchButtons = new JLabel("Select Number of Mouse Buttons");
		lblSearchButtons.setBounds(445, 311, 221, 13);
		ShopForItemsTab.add(lblSearchButtons);

		txtSearchBrand = new JTextField();// field that user enters brand into to search for brand
		txtSearchBrand.setBounds(676, 245, 96, 21);
		ShopForItemsTab.add(txtSearchBrand);
		txtSearchBrand.setColumns(10);

		btnSearchBrand = new JButton("Search by Brand");// button user presses to search by brand
		btnSearchBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search_By_Brand();// when the button is pressed this function is called
			}
		});
		btnSearchBrand.setBounds(793, 245, 191, 21);
		ShopForItemsTab.add(btnSearchBrand);

		spinnerSearchButtonNumber = new JSpinner();// spinner for selecting how much buttons a mouse has
		spinnerSearchButtonNumber
				.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		spinnerSearchButtonNumber.setEditor(new JSpinner.DefaultEditor(spinnerSearchButtonNumber));
		// setting the defaults
		spinnerSearchButtonNumber.setBounds(676, 308, 96, 20);
		ShopForItemsTab.add(spinnerSearchButtonNumber);

		btnSearchMouseButton = new JButton("Search by Button Number");// button for searching the mouses
		btnSearchMouseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the button is pressed this function is called
				Search_By_Mouse_Buttons();
			}
		});
		btnSearchMouseButton.setBounds(793, 307, 191, 21);
		ShopForItemsTab.add(btnSearchMouseButton);

		btnViewAllShoppingItems = new JButton("View All Items");// button that enables the user to view all items again
		btnViewAllShoppingItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the button is pressed this function is called
				Load_All_Stock_Data();
			}
		});
		btnViewAllShoppingItems.setBounds(860, 358, 124, 21);
		ShopForItemsTab.add(btnViewAllShoppingItems);
		Load_Usernames();// calling the functions that load the usernames into the jtable
	}

	private void Clear_Basket() {
		// this function clears the users shopping basket
		customer.clear_shopping_basket();// clearing the basket
		Load_Shopping_Basket();// load the empty shopping basket
	}

	private void Search_By_Mouse_Buttons() {
//this function is called when the search button is pressed
		stock_filtered_by_buttons = fn
				.Search_Item_By_Buttons(Integer.parseInt(spinnerSearchButtonNumber.getValue().toString()));
		// storing the list of stock that have the same number of mouse buttons that the
		// user selected
		display_matching_buttons_items();// call the function that displays the mouses
	}

	private void Log_Out() {
// this function is called when the user logs out
		btnLogIn.setVisible(true);// show the log in button
		chosen_username = null;// set the chosen username to null
		Load_Usernames();// load the jtable with the usernames
		Clear_Fields();// clear all the fields
	}

	private void Search_By_Brand() {
// this function is called when the user presses the search by brand button 
		stock_filtered_by_brand = fn.Search_Item_By_Brand(txtSearchBrand.getText());
		// storing the items that match the brand name
		display_matching_brand_items();// calling the function that displays the items with the matching brand
	}

	private void Get_Selected_Item() {
// this function stores the selected item by the user
		int Index = tblShoppingItems.getSelectedRow();// storing the index of the selected row in the table
		if (!(tblShoppingItems.getValueAt(Index, 0).toString().equals("N/A"))) {
			selected_item = tblShoppingItems.getValueAt(Index, 0).toString();// storing the barcode of the selected item
		}
	}

	private boolean Validate_Card_Details(String card_number, String security_number) {
		// this function is used to validate the card number and security number
		boolean is_card_number_an_int = true;// if the card number is an int
		boolean is_security_number_an_int = true;// if the security number is an int
		boolean valid_card_details = true;// if the details are valid

		try {
			Integer.parseInt(card_number);// check if its an int
		} catch (NumberFormatException e) {
			is_card_number_an_int = false;// if not then this bool is set to false
		}
		try {
			Integer.parseInt(security_number);// check if its an int
		} catch (NumberFormatException e) {
			is_security_number_an_int = false;// if its not an int
		}

		if (is_card_number_an_int == false) {
			valid_card_details = false;// card number is invalid
		}
		if (is_security_number_an_int == false) {
			valid_card_details = false;// security number is invalid
		}
		if (security_number.length() != 3) {
			valid_card_details = false;// security number is invalid
		}
		if (card_number.length() != 6) {
			valid_card_details = false;// card number is in valid
		}

		return valid_card_details;// return the result

	}

	private void Pay_by_Credit_Card() {
//this function is called when the user is paying by credit card
		if (customer.get_Shopping_Basket().isEmpty() == false) {// if there is items in the shopping basket
			String card_number = JOptionPane.showInputDialog(null, "Enter your card number");
			String security_number = JOptionPane.showInputDialog(null, "Enter your security number");
			// storing card details
			if (card_number != null && security_number != null) {// if both have been entered
				boolean valid_details = Validate_Card_Details(card_number, security_number);// validate the details
				if (valid_details == true) {// if card details are valid
					double total = Math.round(customer.get_total_payment() * 100.0) / 100.0;
					// calling the function that calculates the total
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to but these items?",
							"PROCEED WITH PAYMENT?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					// checking if the customer is certain that they want to buy these items.
					if (result == JOptionPane.YES_OPTION) {// if they select yes
						JOptionPane.showMessageDialog(null,
								"£" + total + " paid using Credit Card, and the delivery address is: "
										+ customer.getAddress().toString());
						// display the above message
						HashMap<Integer, Integer> shopping = customer.get_Shopping_Basket();// getting the shopping
																							// basket
						fn.update_stocks_after_purchsase(shopping);// calling the function that updates the stock levels
						fn.Write_To_Stocks_File("Stock.txt");// calling the function that writes the stock to the file
						customer.clear_shopping_basket();// clear the users shopping basket
						Load_Shopping_Basket();// load the empty shopping basket
					}
				} else {// if the card details wasnt valid
					JOptionPane.showMessageDialog(null,
							"Please enter a 6 digit card number and a 3 digit security number");
				}
			}
		} else {// if there was no items in the basket
			JOptionPane.showMessageDialog(null, "Your basket is empty, therefore you can not pay");
		}
	}

	private boolean Validate_Email(String email) {
		// this function is used to validate the email address of the user
		boolean valid_email = true;
		if (email.contains("@") == false | email.contains(".") == false) {
			// if the email doesn't contain an @ or a .
			valid_email = false;// the email is false
		}
		return valid_email;// return the result
	}

	private void Pay_by_Paypal() {
		// this function is called when the user is paying by pay pal
		if (customer.get_Shopping_Basket().isEmpty() == false) {
			String email = JOptionPane.showInputDialog(null, "Enter your paypal email address");// storing email
			if (email != null) {// if email is entered
				boolean valid = Validate_Email(email);// validating the email
				if (valid == true) {// if the email is valid
					double total = Math.round(customer.get_total_payment() * 100.0) / 100.0;
					// store the total
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to but these items?",
							"PROCEED WITH PAYMENT?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					// check if the user is positive that they want to pay
					if (result == JOptionPane.YES_OPTION) {// if they select yes
						JOptionPane.showMessageDialog(null,
								"£" + total + " paid using PayPal, and the delivery address is: "
										+ customer.getAddress().toString());
						// output the message above
						HashMap<Integer, Integer> shopping = customer.get_Shopping_Basket();// . store the users basket
						fn.update_stocks_after_purchsase(shopping);// update the stock file with the new quantities
						fn.Write_To_Stocks_File("Stock.txt");// write the latest stock to the txt file
						customer.clear_shopping_basket();// clear the customers basket
						Load_Shopping_Basket();// load the empty shopping basket
					}
				} else {// if the email is not valid then output this
					JOptionPane.showMessageDialog(null, "Please enter a valid email that contains @ and .");
				}
			}
		} else {// if the basket is empty then output this
			JOptionPane.showMessageDialog(null, "Your basket is empty, therefore you can not pay");
		}
	}

	private void display_matching_brand_items() {
		// this function shows what items match the brand that was inputed
		dtmShoppingItems.setRowCount(0);// clear the table
		try {
			Retail_Price_Comparator RetailComp = new Retail_Price_Comparator();// new instance of the comparator class
			Collections.sort(stock_filtered_by_brand, RetailComp);// sorting the stock list
			for (Stock item : stock_filtered_by_brand) {
				if (item instanceof Keyboard) {// if its a keyboard
					if (item.getQuantity() > 0) {// if its in stock
						Object[] keyboard_data = new Object[] { ((Keyboard) item).getBarcode(),
								((Keyboard) item).getStockType(), ((Keyboard) item).getKeyboardType(),
								((Keyboard) item).getBrand(), ((Keyboard) item).getColour(),
								((Keyboard) item).getConnectivity(), ((Keyboard) item).getQuantity(),
								((Keyboard) item).getRetailPrice(), ((Keyboard) item).getLayout(), "" };
						dtmShoppingItems.addRow(keyboard_data);// add the keyboard to the table
					}
				} else {
					if (item.getQuantity() > 0) {// if there is stock available
						Object[] mouse_data = new Object[] { ((Mouse) item).getBarcode(), ((Mouse) item).getStockType(),
								((Mouse) item).getMouseType(), ((Mouse) item).getBrand(), ((Mouse) item).getColour(),
								((Mouse) item).getConnectivity(), ((Mouse) item).getQuantity(),
								((Mouse) item).getRetailPrice(), "", ((Mouse) item).getNumberOfButtons() };
						dtmShoppingItems.addRow(mouse_data);// add the mouse data to the table
					}
				}
			}
			if (dtmShoppingItems.getRowCount() == 0) {// if no item with the same brand is found
				Object[] empty = new Object[] { "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" };
				dtmShoppingItems.addRow(empty);// display n/a
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());// print this error
		}
	}

	private void Add_To_Shopping_Basket() {
		// this function is used to add an item to the shopping basket
		if (selected_item != null && !(selected_item.equals("N/A"))) {// if an item has been selected
			Stock item = fn.return_item(Integer.parseInt(selected_item));
			// calling the function that returns the item that corresponds to the barcode
			HashMap<Integer, Integer> shopping_basket = new HashMap<Integer, Integer>();
			// creating a new shopping basket
			shopping_basket = customer.get_Shopping_Basket();// shopping basket is equal to the one returned in the
																// customer class
			int quantity_being_bought = Integer.parseInt(spinnerQuantityToBuy.getValue().toString());
			// storing the quantity that the user wants to buy
			int current_stock_quantity = item.getQuantity();// storing the current stocks quantity
			if (shopping_basket.containsKey(item.getBarcode()) == false) {
				// if the shopping basket doesnt contain the item
				if (quantity_being_bought <= current_stock_quantity) {
					// if the quantity desired to be bought is less than or equal to the actual
					// stock quantity
					customer.add_item_to_basket(item.getBarcode(), quantity_being_bought, item);
					Clear_Fields();
					// add the item to the shopping basket
				} else {// if the quantity wanting to be bought exceeds the current stock level
					JOptionPane.showMessageDialog(null, "There is only: " + item.getQuantity()
							+ " left in stock, your chosen amount: " + quantity_being_bought + " exceeds this");
					// output this message
				}
			} else {// if the item is already in the basket
				for (HashMap.Entry<Integer, Integer> entry : shopping_basket.entrySet()) {
					if (shopping_basket.containsKey(item.getBarcode()) == true) {
						int current_quantity_in_basket = entry.getValue();
						// getting the current quantity in the shopping basket
						if (current_quantity_in_basket + quantity_being_bought <= current_stock_quantity) {
							// if the quantity in the shopping basket added to the new quantity is less
							// than or equal to the stock level
							customer.add_item_to_basket(item.getBarcode(), quantity_being_bought, item);
							// add the item to the basket
							Clear_Fields();
						} else {
							// if the amount exceeds the current stock level than output this mesage
							JOptionPane.showMessageDialog(null,
									"There is only: " + item.getQuantity()
											+ " left in stock, your chosen amount exceeds this since you already have: "
											+ current_quantity_in_basket + " in your basket.\n Adding "
											+ quantity_being_bought + " exceeds the current stock level");
							break;// break out of the loop

						}
					}
				}
			}
			selected_item = null;// unselect the item
		} else {
			JOptionPane.showMessageDialog(null,
					"Please reselect the item you want by double clicking it, thank you\n also you can not select an N/A item, thank you");
			// if nothing was selected output this message
		}

		Load_All_Stock_Data();// load the data again
	}

	private void display_matching_buttons_items() {
// this function is used to display the mouses that have the same number of buttons as the user's input
		dtmShoppingItems.setRowCount(0);// clear the table
		try {
			Retail_Price_Comparator RetailComp = new Retail_Price_Comparator();// instance of the comparator class
			Collections.sort(stock_filtered_by_buttons, RetailComp);// sorting the list of stock by retail price
			for (Stock item : stock_filtered_by_buttons) {
				Object[] mouse_data = new Object[] { ((Mouse) item).getBarcode(), ((Mouse) item).getStockType(),
						((Mouse) item).getMouseType(), ((Mouse) item).getBrand(), ((Mouse) item).getColour(),
						((Mouse) item).getConnectivity(), ((Mouse) item).getQuantity(), ((Mouse) item).getRetailPrice(),
						"", ((Mouse) item).getNumberOfButtons() };// add the mouses info to an object
				dtmShoppingItems.addRow(mouse_data);// add the mouses data to the table
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());// if this is not possible then print this error
		}

		if (dtmShoppingItems.getRowCount() == 0) {
			// if there is no mouses that have the same number of buttons requested by the
			// user
			Object[] empty = new Object[] { "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" };
			dtmShoppingItems.addRow(empty);// print n/a to the table
		}
	}

	private void Get_Selected_Username() {
		// this function gets the selected user name from the jtable
		int Index = tblUsernames.getSelectedRow();// getting the selected index of the jtable
		chosen_username = tblUsernames.getValueAt(Index, 0).toString();// storing the username that was selected
	}

	private void determine_tab(String tab) {
		// this function is used to determine what data should be loaded when a new tab
		// is clicked on
		if (tab.equals("View All Items")) {// if we are on the view items tab
			Load_All_Stock_Data();// load all of the stock data immediately
		}
		if (tab.equals("Shop For Items")) {// if we are on the shop for items tab
			Load_All_Stock_Data();// load the table with data immediately
		}
		if (tab.equals("Shopping Basket Tab")) {// if we are on the shopping basket tab
			Load_Shopping_Basket();// load the customers shopping basket immediately
		}
	}

	private void Load_Shopping_Basket() {
// this function is used to load the users shopping basket
		dtmShoppingBasket.setRowCount(0);// clearing the table
		HashMap<Integer, Integer> shopping_basket;// making a shopping basket
		shopping_basket = customer.get_Shopping_Basket();
		// storing the customers shopping basket
		for (Entry<Integer, Integer> entry : shopping_basket.entrySet()) {

			Stock item = fn.return_item(entry.getKey());// getting the item that corresponds to the barcode
			int quantity = entry.getValue();// storing he quantity that the user wants to buy
			if (item instanceof Keyboard) {// if the stock is a keyboard

				Object[] keyboard_data = new Object[] { ((Keyboard) item).getBarcode(),
						((Keyboard) item).getStockType(), ((Keyboard) item).getKeyboardType(),
						((Keyboard) item).getBrand(), ((Keyboard) item).getColour(),
						((Keyboard) item).getConnectivity(), ((Keyboard) item).getRetailPrice(),
						((Keyboard) item).getLayout(), "", quantity };
				dtmShoppingBasket.addRow(keyboard_data);// add the keyboard to the table
			} else {// the product is a mouse
				Object[] mouse_data = new Object[] { ((Mouse) item).getBarcode(), ((Mouse) item).getStockType(),
						((Mouse) item).getMouseType(), ((Mouse) item).getBrand(), ((Mouse) item).getColour(),
						((Mouse) item).getConnectivity(), ((Mouse) item).getRetailPrice(), "",
						((Mouse) item).getNumberOfButtons(), quantity };
				dtmShoppingBasket.addRow(mouse_data);// add the mouse to the table
			}
		}
		lblTotal.setText("Current Total: £" + String.valueOf(Math.round(customer.get_total_payment() * 100.0) / 100.0));
		// show the current total for the products the user has in their shopping basket
	}

	private void Clear_Mouse_Warning_Messages() {
		// this function clears all the validation messages when the user is adding a
		// new mouse
		MouseOriginalCostMessageLabel.setText("");
		MouseRetailPriceMessageLabel.setText("");
		MouseBarcodeMessageLabel.setText("");
		MouseColourMessageLabel.setText("");
		MouseBrandMessageLabel.setText("");
	}

	private void Clear_Keyboard_Warning_Messages() {
		// this function clears all the validation messages when the user is adding a
		// new keyboard
		KeyboardOriginalCostMessageLabel.setText("");
		KeyboardRetailPriceMessageLabel.setText("");
		KeyboardBarcodeMessageLabel.setText("");
		KeyboardColourMessageLabel.setText("");
		KeyboardBrandMessageLabel.setText("");
	}

	private boolean Validate_Mouse_Details() {
		// this function is used to validate the inputs of the user when creating a new
		// mouse
		Clear_Mouse_Warning_Messages();// calling the function that clears all the messages
		boolean all_inputs_valid = true;// indicates if all inputs are valid
		boolean is_barcode_an_int = true;// if the barcode is an int
		boolean is_original_cost_a_double = true;// if the cost is a double
		boolean is_retail_price_a_double = true; // if the retail price s a double
		try {
			Integer.parseInt(txtBarcode.getText());// if the barcode is an int
		} catch (NumberFormatException e) {
			is_barcode_an_int = false;// if the barcode is not an int then set this bool to false
		}
		try {
			Double.parseDouble(txtOriginal_Cost.getText());// if the original cost is a double
		} catch (NumberFormatException e) {
			is_original_cost_a_double = false;// if the original cost is not a doubke then set this bool to false
		}
		try {
			Double.parseDouble(txtRetail_Price.getText());// if the retail price is a double

		} catch (NumberFormatException e) {
			is_retail_price_a_double = false;// if its not a double then set this boolean to false
		}

		if (is_original_cost_a_double != true) {// if the original cost is not a double
			MouseOriginalCostMessageLabel.setText("Please enter a numerical cost");
			all_inputs_valid = false;// set this bool to false as its not a valid input

		}
		if (is_retail_price_a_double != true) {// if the retail price isnt a double
			MouseRetailPriceMessageLabel.setText("Please enter a numerical price");
			all_inputs_valid = false;// its not a valid input
		}

		if (is_barcode_an_int == true) {// if the barcode is an int
			if (txtBarcode.getText().length() == 6) {// if the barcode has 6 digits
				boolean barcode_exists = fn.Check_Existing_Barcodes(Integer.parseInt(txtBarcode.getText()));
				// call a function to check if the barcode already exists =
				if (barcode_exists != false) {// if the barcode does exist

					MouseBarcodeMessageLabel
							.setText("An item with this barcode already exists, please enter a new barcode");
					// output this message
					all_inputs_valid = false;// its not a valid input
				}
			} else {// if the barcode doesnt have 6 digits
				MouseBarcodeMessageLabel.setText("Please enter a barcode that has 6 digits");
				all_inputs_valid = false;// its not a valid input
			}
		} else {
			MouseBarcodeMessageLabel.setText("Please enter a numerical barcode");// if the barcode isnt an int
			all_inputs_valid = false;// its not a valid input
		}
		if (txtBrand.getText().isEmpty() == true) {// if no brand was entered
			MouseBrandMessageLabel.setText("Please enter in a brand");
			all_inputs_valid = false;// not valid
		}
		if (txtColour.getText().matches("[a-zA-Z]+") != true) {
// if the colour has numbers and other characters
			MouseColourMessageLabel
					.setText("Please enter a colour that has no integers or special characters/ no spaces, thank you ");
			all_inputs_valid = false;// its not a valid input
		}

		return all_inputs_valid;// return the bool

	}

	private boolean Validate_Keyboard_Details() {
		// this function is used to validate the user input for the new keyboard being
		// created
		Clear_Keyboard_Warning_Messages();// clears the previous waring messages
		boolean all_inputs_valid = true;// stores whether all inputs are true
		boolean is_barcode_an_int = true;// if the barcode is an int
		boolean is_original_cost_a_double = true;// if the cost is a double
		boolean is_retail_price_a_double = true;// if the retail price is a double

		try {
			Integer.parseInt(txtKeyboardBarcode.getText());// check if the barcode can be converted as an int
		} catch (NumberFormatException e) {
			is_barcode_an_int = false;// if not then set this variable to false
		}
		try {
			Double.parseDouble(txtKeyboardOriginalCost.getText());// checks if the original cost is a double
		} catch (NumberFormatException e) {
			is_original_cost_a_double = false;// if its not then set this variable to false
		}
		try {
			Double.parseDouble(txtKeyboardRetailPrice.getText());// checks if the retail price is a double
		} catch (NumberFormatException e) {
			is_retail_price_a_double = false;// if its not then set this variable to false
		}

		if (txtKeyboardBrand.getText().isEmpty() == true) {
			all_inputs_valid = false;// if there is no brand name entered , set valid inputs to false
			KeyboardBrandMessageLabel.setText("Please enter in a brand");// display this message
		}
		if (is_original_cost_a_double != true) {// if the original cost isnt a double
			KeyboardOriginalCostMessageLabel.setText("Please enter a numerical cost");// show this message
			all_inputs_valid = false;// set the valid inputs to false

		}
		if (is_retail_price_a_double != true) {// if the retail price isnt a double
			KeyboardRetailPriceMessageLabel.setText("Please enter a numerical price");// show this message
			all_inputs_valid = false;// set this bool to false
		}

		if (is_barcode_an_int == true) {// if the barcode is an integer
			if (txtKeyboardBarcode.getText().length() == 6) {// if the length of the barcode is 6 digits
				boolean barcode_exists = fn.Check_Existing_Barcodes(Integer.parseInt(txtKeyboardBarcode.getText()));
				// call the function that checks if the barcode already exists in the system
				if (barcode_exists != false) {
					// if the barcode exists already
					KeyboardBarcodeMessageLabel
							.setText("An item with this barcode already exists, please enter a new barcode");
					// output this message
					all_inputs_valid = false;// set this bool to false
				}
			} else {// if the barcode is not 6 digits its not valid
				KeyboardBarcodeMessageLabel.setText("Please enter a barcode that has 6 digits");// output this message
				all_inputs_valid = false;// set this bool to false
			}
		} else {// if the barcode isnt an int
			KeyboardBarcodeMessageLabel.setText("Please enter a numerical barcode");// output this message
			all_inputs_valid = false;// set this bool to false
		}
		if (txtKeyboardColour.getText().matches("[a-zA-Z]+") != true) {
			// if there is other characters other than letters in the colour inputed
			KeyboardColourMessageLabel.setText("Please enter a colour that has no integers or special characters ");
			// output this message
			all_inputs_valid = false;// set the bool to false as its not a valid input
		}
		return all_inputs_valid;// return the bool
	}

	private void Add_Mouse() {
		// this function is called when the user wants to add a new mouse to the system
		boolean valid_inputs = Validate_Mouse_Details();// calls a function that validates the user inputs
		if (valid_inputs == true) {// if everything is valid
			fn.Read_Stock("Stock.txt");// reading to get the latest data
			fn.Create_New_Mouse(Integer.parseInt(txtBarcode.getText()),
					(cmbMouseType.getSelectedItem()).toString().toLowerCase(), txtBrand.getText(), txtColour.getText(),
					(cmbConnectivity.getSelectedItem()).toString(),
					Integer.parseInt(spinnerQuantity.getValue().toString()),
					Double.parseDouble(txtOriginal_Cost.getText()), Double.parseDouble(txtRetail_Price.getText()),
					Integer.parseInt(spinnerMouseButtons.getValue().toString()));
			// call the function that creates the new keyboard with the user inputs as
			// parameters
			fn.Write_To_Stocks_File("Stock.txt");// call the function that writes the new data to the stock file
			Clear_Fields();// clear the input fields so that a new input can be made
		} else {// if there was any invalid input
			JOptionPane.showMessageDialog(null, "Please make sure all inputs are valid, thanks");// print this message
		}
	}

	private void Add_Keyboard() {
		// this function is called when the user clicks on the button for adding a new
		// keyboard
		boolean valid_inputs = Validate_Keyboard_Details();// it calls a function used to validate the user input
		if (valid_inputs == true) {// if all inputs are valid
			fn.Read_Stock("Stock.txt");// reading to get the latest data
			fn.Create_New_Keyboard(Integer.parseInt(txtKeyboardBarcode.getText()),
					(cmbKeyboardType.getSelectedItem()).toString().toLowerCase(), txtKeyboardBrand.getText(),
					txtKeyboardColour.getText(), (cmbKeyboardConnectivty.getSelectedItem()).toString(),
					Integer.parseInt(spinnerKeyboardQuantity.getValue().toString()),
					Double.parseDouble(txtKeyboardOriginalCost.getText()),
					Double.parseDouble(txtKeyboardRetailPrice.getText()),
					(cmbKeyboardLayout.getSelectedItem()).toString().toLowerCase());
			// calls the function that creates a new keyboard the new keyboard to the stock
			// file
			fn.Write_To_Stocks_File("Stock.txt");// calls the function that writes the new stock data to the stock file
			Clear_Fields();// clearing all the fields
		} else {
			JOptionPane.showMessageDialog(null, "Please make sure all inputs are valid, thanks");
			// if inputs are not valid then display this message
		}
	}

	private void Login() { // this function is called when the user presses the log in button
		if (chosen_username != null) {
// checking if a username has been chosen
			is_user_admin = fn.Check_User_Type(chosen_username);// calling a function to check if the user is an admin
																// or not
			if (is_user_admin == true) {
// if they are an admin show these tabs
				tabbedPane.addTab("View All Items", null, ViewAllItemsTab, null);
				tabbedPane.addTab("Add New Mouse", null, AddMouseTab, null);
				tabbedPane.addTab("Add New Keyboard", null, AddKeyboardTab, null);
				btnLogIn.setVisible(false);// hide the login button

			} else {// if they are a customer then show these tabs
				tabbedPane.addTab("Shop For Items", null, ShopForItemsTab, null);
				tabbedPane.addTab("Shopping Basket Tab", null, ShoppingBasketTab, null);
				customer = fn.get_user(chosen_username);
				basketLabel.setText("Shopping basket for: " + customer.getName());
				// set the shopping basket label to the name of the user
				btnLogIn.setVisible(false);// hide the log in button
			}

		} else {// if there was no username selected
			JOptionPane.showMessageDialog(null,
					"Please ensure that you have selected a user from the list by\n double clicking on a username, thank you");
			// display the above message
		}
	}

	private void Clear_Fields() {
		// this functions purpose is to clear the fields so that new data can be entered
		txtBarcode.setText("");
		txtBrand.setText("");
		txtColour.setText("");
		spinnerQuantity.setValue(1);
		txtOriginal_Cost.setText("");
		txtRetail_Price.setText("");
		spinnerMouseButtons.setValue(2);
		txtKeyboardBarcode.setText("");
		txtKeyboardBrand.setText("");
		txtKeyboardColour.setText("");
		txtKeyboardOriginalCost.setText("");
		spinnerKeyboardQuantity.setValue(1);
		txtKeyboardRetailPrice.setText("");
		spinnerQuantityToBuy.setValue(1);
		txtSearchBrand.setText("");
		spinnerSearchButtonNumber.setValue(1);

	}

	private void Load_All_Stock_Data() {

		// this function loads all the stock data from the stock file into a jtable so
		// the user can view the stock
		dtmViewAllItems.setRowCount(0);// clearing the data in the table that the admin can view
		dtmShoppingItems.setRowCount(0);// clearing the data in the table that the customer can view
		try {
			stock_list = fn.Read_Stock("Stock.txt");// storing the data from the stock file into a list
			Retail_Price_Comparator RetailComp = new Retail_Price_Comparator();// creating a new instance of the
			// comparator class used to sort the stock data
			Collections.sort(stock_list, RetailComp);// passing in the stock list into the comparator class
			for (Stock item : stock_list) {// for each stock in in the stock list
				if (item instanceof Keyboard) {// if the item is a keyboard
					if (is_user_admin == true) {// if the user logged in is a user
						Object[] keyboard_data = new Object[] { ((Keyboard) item).getBarcode(),
								((Keyboard) item).getStockType(), ((Keyboard) item).getKeyboardType(),
								((Keyboard) item).getBrand(), ((Keyboard) item).getColour(),
								((Keyboard) item).getConnectivity(), ((Keyboard) item).getQuantity(),
								((Keyboard) item).getOriginalCost(), ((Keyboard) item).getRetailPrice(),
								((Keyboard) item).getLayout(), "" };
						// creating an object that consists of the keyboards details
						dtmViewAllItems.addRow(keyboard_data);// adding the keyboard details to the table
					} else {// if the user is a customer
						if (item.getQuantity() > 0) {// if the item isnt out of stock
							Object[] keyboard_data = new Object[] { ((Keyboard) item).getBarcode(),
									((Keyboard) item).getStockType(), ((Keyboard) item).getKeyboardType(),
									((Keyboard) item).getBrand(), ((Keyboard) item).getColour(),
									((Keyboard) item).getConnectivity(), ((Keyboard) item).getQuantity(),
									((Keyboard) item).getRetailPrice(), ((Keyboard) item).getLayout(), "" };
							// making an object that consists of the keyboards details except their original
							// price
							dtmShoppingItems.addRow(keyboard_data);// adding the keyboard info to the table

						}
					}
				} else {// if its a mouse instead
					if (is_user_admin == true) {// if the user isan admin
						Object[] mouse_data = new Object[] { ((Mouse) item).getBarcode(), ((Mouse) item).getStockType(),
								((Mouse) item).getMouseType(), ((Mouse) item).getBrand(), ((Mouse) item).getColour(),
								((Mouse) item).getConnectivity(), ((Mouse) item).getQuantity(),
								((Mouse) item).getOriginalCost(), ((Mouse) item).getRetailPrice(), "",
								((Mouse) item).getNumberOfButtons() };
						// create an object that contains all the mouses data
						dtmViewAllItems.addRow(mouse_data);// add the mouse to the table so that it can be displayed
					} else {// if the user is a customer
						if (item.getQuantity() > 0) {// if the item isnt out of stock
							Object[] mouse_data = new Object[] { ((Mouse) item).getBarcode(),
									((Mouse) item).getStockType(), ((Mouse) item).getMouseType(),
									((Mouse) item).getBrand(), ((Mouse) item).getColour(),
									((Mouse) item).getConnectivity(), ((Mouse) item).getQuantity(),
									((Mouse) item).getRetailPrice(), "", ((Mouse) item).getNumberOfButtons() };
							// create an object consisting of the mouses data except the original price
							dtmShoppingItems.addRow(mouse_data);// add the mouse to the table
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());// if its not possible then it prints an error message to the console
		}

	}

	private void Load_Usernames() {
		// this function loads the usernames in the file into a table so that the user
		// can select username
		tabbedPane.remove(ShoppingBasketTab);// removing the tabs so that the user can only see the login tab
		tabbedPane.remove(AddMouseTab);
		tabbedPane.remove(AddKeyboardTab);
		tabbedPane.remove(ViewAllItemsTab);
		tabbedPane.remove(ShopForItemsTab);
		dtmUsernames.setRowCount(0);// clearing the list of usernames
		try {

			user_list = fn.Read_User_Accounts("UserAccounts.txt");// storing the usernames read from the user file

			for (System_User users : user_list) {
				Object[] usernames = new Object[] { users.getUsername() };

				dtmUsernames.addRow(usernames);// adding the usernames to the table
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());// if its not able to do so then it prints this error to the console
		}

	}
}
