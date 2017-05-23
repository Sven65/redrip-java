package xyz.mackan.redrip.parser.filetype;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GIFV {
	
	private String URL;
	
	public GIFV(String url){
		URL = url;
	}
	
	public String toMP4() throws IOException{
		Document doc = Jsoup.connect(this.URL).get();
		
		Elements sourceEl = doc.getElementsByTag("source");
		Element src = sourceEl.get(0);
		String fileURL = "http:"+src.attr("src");
		
		return fileURL;
	}
}
