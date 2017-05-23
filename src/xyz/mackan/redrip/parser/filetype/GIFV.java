package xyz.mackan.redrip.parser.filetype;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GIFV {
	
	private String URL;
	
	/**
	 * Creates a new GIFV parser
	 * @param url
	 */
	public GIFV(String url){
		URL = url;
	}
	
	/**
	 * Parses a GIFV to MP4
	 * @return
	 * @throws IOException
	 */
	public String toMP4() throws IOException{
		Document doc = Jsoup.connect(this.URL).get();
		
		Elements sourceEl = doc.getElementsByTag("source");
		Element src = sourceEl.get(0);
		String fileURL = "http:"+src.attr("src");
		
		return fileURL;
	}
}
