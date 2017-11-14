package dataPackeges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComplexProduct {

	public String title;
	public String id;
	private List<PriceToLink> priceToLinkList = new ArrayList<>();

	public ComplexProduct(String titles, String id, float price, String link) {
		this.title = titles;
		this.id = id;
		priceToLinkList.add(new PriceToLink(price, link));
	}
	
	public void addNewPriceToLik(Product p, String link) {
		priceToLinkList.add(new PriceToLink(p.price, link));
		sortList();
	}
	
	private void sortList() {
		if (priceToLinkList.size() > 2) {
			  Collections.sort(priceToLinkList, new Comparator<PriceToLink>() {
				@Override
				public int compare(PriceToLink arg0, PriceToLink arg1) {
					return arg0.getPrice().compareTo(arg1.getPrice());
				}
			  });
			}
	}

	@Override
	public String toString() {
		String text = "";
		text += "Title: " + title + " Id: " + id;
		for (PriceToLink priceToLink : priceToLinkList) {
			text += " price: " + priceToLink.price + " link: "+ priceToLink.link;
		}
		return text;
	}

}
