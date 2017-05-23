package xyz.mackan.redrip.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import xyz.mackan.redrip.StringHelper;
import xyz.mackan.redrip.parser.filetype.GIFV;

public class Parser {
	public Parser(){
		
	}
	
	
	/**
	 * Gets all the available parsers
	 * @author Mackan
	 * @return The available parsers
	 */
	public ArrayList<String> getParsers(){
		ArrayList<String> parsers = new ArrayList<String>();
		parsers.addAll(Arrays.asList("awwnime", "gfycat", "redditbooruu", "tumblr"));
		return parsers;
	}
	
	/**
	 * Parses the HTML of the page specified by the url param for any file links
	 * @param url The url to parse
	 * @param extensions The extensions to use
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	public String doParse(String url, ArrayList<String> extensions) throws URISyntaxException, IOException{
		StringHelper SH = new StringHelper();
		String domain = SH.getDomain(url);
		
		System.out.println("DOMAIN: "+domain);
		
		GfyCat gfycat = new GfyCat();
		
		String fileURL = null;
		
		switch(domain){
			case "gfycat.com":
				if(extensions.contains("webm")){
					fileURL = gfycat.parse(url, "webm");
				}else if(extensions.contains("mp4")){
					fileURL = gfycat.parse(url, "mp4");
				}
			break;
		}
		
		return fileURL;
	}
	
	public String gifvToMP4(String url) throws IOException{
		GIFV gifv = new GIFV(url);
		return gifv.toMP4();
	}
}
