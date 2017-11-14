package threads;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dataPackeges.DataCel;
import dataPackeges.DataEmag;
import dataPackeges.Product;
import interfaces.IDataPage;
import main.Main;

public class ThreadParsing implements Runnable {

	public Thread t;
	private IDataPage dpEmag;
	private IDataPage dbCel;
	private String urlEmag;
	private String urlCel;
	private int tid;

	public ThreadParsing(int tid) {
		this.tid = tid;
		this.dpEmag = new DataEmag();
		this.dbCel = new DataCel();
		int noOfPage = tid + 1;
		this.urlEmag = "https://www.emag.ro/casti-pc/p" + noOfPage + "/c";
		this.urlCel = "http://www.cel.ro/casti/0a-" + noOfPage;
	}

	@Override
	public void run() {
		try {
			getDataFromSite();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getDataFromSite() throws IOException {
		Document documentEmag = Jsoup.connect(urlEmag).followRedirects(false).get();
		Document documentCel = Jsoup.connect(urlCel).followRedirects(false).get();
		dpEmag.setDocument(documentEmag);
		dbCel.setDocument(documentCel);

		Main.m.Pmutex(tid);
		for (Product element : dpEmag.getProducts()) {
			Main.addToList(element);
		}

		for (Product element : dbCel.getProducts()) {
			Main.addToList(element);
		}
		System.out.println(Main.mata);
		Main.mata++;
		Main.m.Vmutex(tid);
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}