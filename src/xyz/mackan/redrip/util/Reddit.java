package xyz.mackan.redrip.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class Reddit {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public Reddit(){
		
	}
	
	/**
	 * Gets the data from a subreddit
	 * @param sub
	 * @param amount
	 * @param sort
	 * @param after
	 * @param log
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getRedditData(String sub, int amount, String sort, String after, TextArea log) throws Exception{
		
		JSONParser parser = new JSONParser();
		
		
		String url = String.format("https://www.reddit.com/r/%s/%s.json?limit=%d", sub, sort, amount);
		if(after != null && !after.equals("")){
			url += String.format("&after=t3_%s", after);
		}
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(request);

		log.appendText("Sending 'GET' request to URL : " + url+"\n");
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
		
		if(statusCode == 200){
			return jsonObject;
		}else{
			String message = (String) jsonObject.get("message");
			String reason = (String) jsonObject.get("reason");
			
			if(reason.equals("null")){
				reason = "";
			}
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Can't access subreddit");
			alert.setContentText(String.format("Can't access subreddit %s, %s (%s)", sub, reason, message));
			alert.showAndWait();
			
			return null;
		}
	}
}
