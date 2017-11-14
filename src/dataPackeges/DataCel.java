package dataPackeges;

import java.util.*;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import interfaces.IDataPage;

public class DataCel implements IDataPage {

	private Document document;
	private Elements ids;
	private Elements prices;
	private Elements titles;
	private Elements links;

	private void getPrice() {
		prices = document.select("b[itemprop=price]");
	}

	private void getId() {
		ids = document.getElementsByClass("stoc_list");
	}

	private void getTitle() {
		titles = document.getElementsByClass("productTitle");
	}

	private void getLink() {
		links = document.getElementsByClass("productTitle");
	}
	
	private String trimId(String id ) {
		String result = id.substring(id.indexOf("s") + 1, id.indexOf("-"));
		return result;
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
			id = trimId(ids.get(i).getElementsByAttribute("id").attr("id"));
			float price = Float.valueOf(prices.get(i).text());
			String link = links.get(i).select("a").first().attr("href");
			Product p = new Product(title, id, price, link);
			listProducts.add(p);
		}
		return listProducts;
	}

	@Override
	public void setDocument(Document d) {
		this.document = d;
	}

}
