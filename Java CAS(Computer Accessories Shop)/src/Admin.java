public class Admin extends System_User {
// this class is a child of the system user class
	public Admin(int ID, String username, String name, User_Address address, String user_type) {
		// this is the constructor for the admin class
		super(ID, username, name, address, user_type);// passing the parameters to the superclass
	}

	public String toString() {
		// Overrides the to string class
		return getName() + getID();// returning the name and the id of the admin
	}
}
