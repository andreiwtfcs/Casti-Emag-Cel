package dataPackeges;

public class PriceToLink {
	
	public float price;
	public String link;

	public PriceToLink(float price, String link) {
		this.price = price;
		this.link = link;
	}

	public Float getPrice() {
		return new Float(price);
	}
}
