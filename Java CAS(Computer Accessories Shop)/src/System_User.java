public abstract class System_User {
// this function contains all the attributes and methods each admin and customer will have
	private String name;// the name of the user
	private int ID;// the id of the user
	private String username;// the username of the user
	private User_Address address;// the address of the user
	private String user_type;// the type of user it is

	public System_User(int ID, String username, String name, User_Address address, String user_type) {
		// the constructor of the system user class
		this.name = name;// Initialising the name
		this.ID = ID;// Initialising the id
		this.username = username;// Initialising the username
		this.address = address;// Initialising the address
		this.user_type = user_type;// Initialising the user type
	}

	public String getName() {
		// this function returns the name of the user
		return this.name;
	}

	public int getID() {
		// this function returns the id of the user
		return this.ID;
	}

	public String getUsername() {
		// this function returns the username of the user
		return this.username;
	}

	public User_Address getAddress() {
		// this function returns the address of the user
		return this.address;
	}

	public String getUserType() {
		// this function returns the type of user
		return this.user_type;
	}
}
