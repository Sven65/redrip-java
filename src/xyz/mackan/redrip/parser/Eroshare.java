package xyz.mackan.redrip.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Eroshare {
	public Eroshare(){
		
	}
	
	public String doParse(String URL) throws IOException{
		Document doc = Jsoup.connect(URL).get();
		
		Elements sourceEl = doc.getElementsByTag("source");
		Element src = sourceEl.get(0);
		String fileURL = "http:"+src.attr("src");
		
		return fileURL;
	}
}
