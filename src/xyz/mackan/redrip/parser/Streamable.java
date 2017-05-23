package xyz.mackan.redrip.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Streamable {
	public Streamable(){
		
	}
	/**
	 * Gets the file URL from a streamable.com document
	 * @param URL
	 * @return
	 * @throws IOException
	 */
	public String parse(String URL) throws IOException{
		Document doc = Jsoup.connect(URL).get();
		
		Elements sourceEl = doc.getElementsByTag("source");
		Element src = sourceEl.get(0);
		String fileURL = "http:"+src.attr("src");
		
		return fileURL;
	}
}
