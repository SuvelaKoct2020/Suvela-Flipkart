package Asssignment.flipkart;

public class Details extends IphoneData implements Comparable<Details> {
	
	String name = "";
	int Price = 0;
	String ratings = "";

	public Details(String name, int Price, String ratings) {
		super();

		this.name = name;
		this.Price = Price;
		this.ratings = ratings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int Price) {
		this.Price = Price;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}
	
	
			@Override
			public int compareTo(Details d) {
				return this.getName().compareTo(d.getName());


		}
	

	

}



