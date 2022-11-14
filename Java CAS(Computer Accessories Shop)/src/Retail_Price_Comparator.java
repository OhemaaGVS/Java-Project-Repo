import java.util.Comparator;// importing the comparator

public class Retail_Price_Comparator implements Comparator<Stock> {
	// this class is used to sort the stock in descending order based on their
	// retail price
	public int compare(Stock stock1, Stock stock2) {
		// this function sorts the stock
		if (stock1.getRetailPrice() > (stock2.getRetailPrice())) {
// if the retail price of the first stock is greater than the second item of stock
			return 1;// its in the correct position
		} else {
			return -1;// its not in the correct position
		}
	}
}