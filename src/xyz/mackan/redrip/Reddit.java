package xyz.mackan.redrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Reddit {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public Reddit(){
		
	}
	
	public JSONObject getRedditData(String sub, int amount, String sort, String after) throws Exception{
		
		JSONParser parser = new JSONParser();
		
		
		String url = String.format("https://www.reddit.com/r/%s/%s.json?limit=%d", sub, sort, amount);
		if(after != null){
			url += String.format("&after=t3_{}", after);
		}
		
		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

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
