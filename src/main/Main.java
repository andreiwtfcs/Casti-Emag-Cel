package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataPackeges.ComplexProduct;
import dataPackeges.Product;
import threads.ThreadParsing;

public class Main {
	public static Map<String, ComplexProduct> LISTOFPRODUCTS = new HashMap<String, ComplexProduct>();
	public static int NUMBEROFTHREDS = 120;
	public static int mata = 0;

	public static Dekker m = new Dekker();
	
	public static void main(String[] args) throws Exception {
		List<ThreadParsing> listOfThreads = new ArrayList<>();
		for (int i = 0; i < NUMBEROFTHREDS; i++) {
			ThreadParsing t = new ThreadParsing(i);
			listOfThreads.add(t);
			t.start();
		}
		
		for (ThreadParsing t : listOfThreads) {
			t.t.join();
		}
		
		for (Entry<String, ComplexProduct> entry : LISTOFPRODUCTS.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

	public static void addToList(Product p) {
		if (LISTOFPRODUCTS.get(p.id) != null) {
			LISTOFPRODUCTS.get(p.id).addNewPriceToLik(p, p.link);
		} else {
			LISTOFPRODUCTS.put(p.id, new ComplexProduct(p.title, p.id, p.price, p.link));
		}
	}
}
