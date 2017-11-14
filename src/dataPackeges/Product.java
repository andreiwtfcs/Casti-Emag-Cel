package dataPackeges;

public class Product {

	public String title;
	public String id;
	public float price;
	public String link;

	public Product(String titles, String id, float price, String link) {
		this.title = titles;
		this.id = id;
		this.price = price;
		this.link = link;
	}

	@Override
	public String toString() {
		return "Title: " + title + " Id: " + id + " Price: " + price + " link: " + link;
	}
}