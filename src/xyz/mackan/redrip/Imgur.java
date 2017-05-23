package xyz.mackan.redrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.control.TextArea;

public class Imgur {
	
	private String API_KEY;
	private final String API_BASE = "https://api.imgur.com/3";
	private final String USER_AGENT = "Mozilla/5.0";
	
	/**
	 * Creates a new Imgur api wrapper
	 * @param API_KEY
	 */
	public Imgur(String API_KEY){
		this.API_KEY = API_KEY;
	}
	
	/**
	 * Gets the images of an album
	 * @param album The hash of the album to get
	 * @param log The main GUIs log area
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getAlbumImages(String album, TextArea log) throws Exception{
		String API_URL = String.format("%s/album/%s/images", API_BASE, album);
		
		JSONParser parser = new JSONParser();
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(API_URL);
		
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Authorization", String.format("Client-ID %s", this.API_KEY));
		
		HttpResponse response = client.execute(request);

		log.appendText("Sending 'GET' request to URL : " + API_URL +"\n");
		int statusCode = response.getStatusLine().getStatusCode();
		log.appendText("Response Code : " +statusCode+"\n");
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		String toParse = result.toString();
		
		Object obj = parser.parse(toParse);
		JSONObject jsonObject = (JSONObject) obj;
		
		return jsonObject;
	}
}
