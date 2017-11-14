package dataPackeges;
import java.util.*;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import interfaces.IDataPage;
public class DataEmag implements IDataPage{

	private Document document;
	private Elements ids;
	private Elements prices;
	private Elements titles;
	private Elements links;

	private void getPrice() {
		prices = document.getElementsByClass("product-new-price");
	}

	private void getId() {
		ids = document.select("input[name=product[]]");
	}

	private void getTitle() {
		titles = document.getElementsByClass("card-body product-title-zone");
	}

	private void getLink() {
		links = document.getElementsByClass("card-body product-title-zone");
	}
	
	private float getNumber(String price) {
		price = price.trim();
		if (price.contains(" ")) {
			price = price.substring(0, price.indexOf(" "));
		}
		return Float.valueOf(price) / 100;
	}
	
	@Override
	public void setDocument(Document d) {
		document = d;
	}

	@Override
	public List<Product> getProducts() {
		getPrice();
		getId();
		getTitle();
		getLink();
		List<Product> listProducts = new ArrayList<>();
		for (int i = 0; i < ids.size(); i++) {
			String title = titles.get(i).text();
			String id = "";
			id = ids.get(i).attr("value");
			float price = getNumber(prices.get(i).text());
			String link = links.get(i).select("a").first().attr("href");
			Product p = new Product(title, id, price, link);
			listProducts.add(p);
		}
		return listProducts;
	}
}
