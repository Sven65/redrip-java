package xyz.mackan.redrip;

import java.util.ArrayList;

public class StringHelper {
	public StringHelper(){
		
	}
	
	public String getFilename(String url){
		return url.substring(url.lastIndexOf('/')+1, url.length());
	}
	
	public String getExtension(String url){
		return url.substring(url.lastIndexOf(".") + 1).toLowerCase();
	}
	
	public Boolean isDirectLink(String url, ArrayList<String> extensions){
		String extension = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
		
		return extensions.contains(extension);
	}
	
	
}
