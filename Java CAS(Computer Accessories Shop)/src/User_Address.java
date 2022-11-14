public class User_Address {
//this class is used to create the address of the users
	private int house_number;// stores the house number
	private String postcode;// stores the users postcode
	private String city;// stores the city of the user

	public User_Address(int house_number, String postcode, String city) {
		// this is the constructor of the user address class
		this.house_number = house_number;// Initialising the house number
		this.postcode = postcode;// Initialising the post code
		this.city = city;// Initialising the city
	}

	public int Gethouse_number() {
		// this function returns the house number of the user
		return this.house_number;
	}

	public String Getpostcode() {
		// this function returns the postcode of the user
		return this.postcode;
	}

	public String Getcity() {
		// this function returns the city of the user
		return this.city;
	}

	public String toString() {
// this function returns a string that consists of the house number, post code and the city the user resides in
		String value = house_number + " " + postcode + " " + city;
		return value;
	}

}
