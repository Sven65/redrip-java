package xyz.mackan.redrip.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GfyCat {
	public GfyCat(){
		
	}
	
	/**
	 * Parses a page by URL and returns all file links
	 * @param url The URL to parse
	 * @param format The file format to return
	 * @return Files
	 * @throws IOException
	 */
	public String parse(String url, String format) throws IOException{
		
		
		Document doc = Jsoup.connect(url).get();
		
		Elements videos = doc.getElementsByTag("video");
		
		String webm = null;
		String mp4 = null;
		
		if(videos != null){
			Element video = videos.get(0);
			Elements videoSources = video.getElementsByTag("source");
			
			for(Element source : videoSources){
				String type = source.attr("type");
				if(type.equals("video/webm")){
					webm = source.attr("src");
				}else if(type.equals("video/mp4")){
					mp4 = source.attr("src");
				}
			}
		}
		
		if(format.equals("webm")){
			return webm;
		}else if(format.equals("mp4")){
			return mp4;
		}else{
			return null;
		}
	}
}
