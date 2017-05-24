package xyz.mackan.redrip.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	public StringHelper(){
		
	}
	
	/**
	 * Gets the domain of an URL
	 * @param url
	 * @return String
	 * @throws URISyntaxException
	 */
	public String getDomain(String url) throws URISyntaxException{
		URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain.startsWith("www.") ? domain.substring(4) : domain;
	}
	
	/**
	 * Gets the filename from a URL
	 * @param url
	 * @return String
	 */
	public String getFilename(String url){
		return url.substring(url.lastIndexOf('/')+1, url.length());
	}
	
	/**
	 * Gets the extension of a URL
	 * @param url
	 * @return String
	 */
	public String getExtension(String url){
		return url.substring(url.lastIndexOf(".") + 1).toLowerCase();
	}
	
	/**
	 * Checks if the url is a direct link with one of the specified extensions
	 * @param url
	 * @param extensions
	 * @return Boolean
	 */
	public Boolean isDirectLink(String url, ArrayList<String> extensions){
		String extension = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
		
		return extensions.contains(extension);
	}
	
	/**
	 * Checks if the url leads to imgur
	 * @param url
	 * @return Boolean
	 */
	public Boolean isImgurLink(String url){
		String pattern = "https?:\\/\\/(m\\.)?imgur\\.com\\/a\\/.*$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(url);
		if(m.matches()){
			return true;
		}
		return false;
	}
	
	public Boolean isImgurAlbum(String url) throws URISyntaxException{
		String domain = this.getDomain(url);
		if(domain.equals("imgur.com") || domain.equals("m.imgur.com")){
			String ext = this.getExtension(url);
			System.out.println("EXT = "+ext);
			if(ext.contains("com/")){
				return true;
			}
		}
		return false;
	}
	
	
}
