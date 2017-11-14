package interfaces;

import java.util.List;

import org.jsoup.nodes.Document;

import dataPackeges.Product;

public interface IDataPage {
	
	public List<Product> getProducts();
	
	public void setDocument(Document d);

}
