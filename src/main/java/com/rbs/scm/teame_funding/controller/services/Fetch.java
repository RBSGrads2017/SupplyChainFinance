package com.rbs.scm.teame_funding.controller.services;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.rbs.scm.teame_funding.model.pojos.Libor;

public class Fetch {
	public String fetch() {
//    	ArrayList<String> libor_duration = new ArrayList<String>();
//		ArrayList<String> libor_rate = new ArrayList<String>();
		
		ArrayList<Libor> libors = new ArrayList<>();
		Libor libor ;
		
		
		try {
			
			ArrayList<String> urls = new ArrayList<String>();
			urls.add("https://www.homefinance.nl/english/international-interest-rates/libor/libor-interest-rates-usd.asp");
			urls.add("https://www.homefinance.nl/english/international-interest-rates/libor/libor-interest-rates-gbp.asp");
			urls.add("https://www.homefinance.nl/english/international-interest-rates/libor/libor-interest-rates-jpy.asp");
			urls.add("https://www.homefinance.nl/english/international-interest-rates/libor/libor-interest-rates-chf.asp");
			urls.add("https://www.homefinance.nl/english/international-interest-rates/libor/libor-interest-rates-eur.asp");
			
			for(String url:urls) {
				
				Document doc = Jsoup.connect(url).get();
				
				Elements tables = doc.select("table[class^=\"FormTable\"]");
//				System.out.println(tables);
				
				Elements data_rows = tables.select("tr[class^=\"tabledata\"]");
//				System.out.println(data_rows);

				boolean flag = true;
				for(int i=0; i<data_rows.size(); i++) {
					if(flag) {
						flag = false;
						continue;
					}
					Elements cell = data_rows.get(i).select("td");
					if(cell.get(1).text().equals("-"))
						continue;
					else {
						String[] s = cell.get(0).text().split(" ");
						libor = new Libor();
						if(s[0].equals("US")) {
							libor.setCurrency(s[0]+" "+s[1]+" "+s[2]);
							libor.setDuration(s[3]+" "+s[4]);
						}
						else {
							libor.setCurrency(s[0]+" "+s[1]);
							libor.setDuration(s[2]+" "+s[3]);
						}
						
						libor.setRate(cell.get(1).text());
						
						libors.add(libor);
					}
				}
				
			}
			
			for(int i=0; i<libors.size(); i++) {
				System.out.println(libors.get(i).getDuration() + " " + libors.get(i).getRate());
			}
			
			String json = new Gson().toJson(libors);
			//String json = new Gson().toJson(libor);
//			System.out.print(json);
			return json;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
    }
}
