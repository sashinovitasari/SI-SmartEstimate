package weather;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.omg.CORBA.portable.InputStream;

import sun.net.www.protocol.http.HttpURLConnection;

public class InfoWeather {
	public static int TODAY = 0;
	public static int TOMORROW = 1;
	public static int NEXT_OF_TOMORROW = 2;
	public static int NEXT_NEXT_OF_TOMORROW = 3;
	
	/*
	 * curl https://query.yahooapis.com/v1/public/yql 
	 * -d q="select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Bandung\")" 
	 * -d format=json
	 */
	public static JSONObject fetchWeatherInfo() throws Exception {
		String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
		String query = "select * from weather.forecast where woeid in "
				+ "(select woeid from geo.places(1) where text='Bandung')";
		String fullUrlStr = baseUrl + URLEncoder.encode(query, "UTF-8") + "&format=json";

		URL fullUrl = new URL(fullUrlStr);
		HttpURLConnection is = (HttpURLConnection) fullUrl.openConnection();
		is.connect();
		
		BufferedInputStream in = new BufferedInputStream(is.getInputStream());

		JSONTokener tok = new JSONTokener(in);
		JSONObject result = new JSONObject(tok);
		
		//System.out.println(result);
		is.disconnect();
		
		return result;
	}
	
	public static void addDatabase() {
		
	}
	
	public static JSONObject showWeatherInfo(int day) {
		JSONObject json;
		JSONArray jarray;
		try {
			json = fetchWeatherInfo();
			json = json.getJSONObject("query").getJSONObject("results")
					.getJSONObject("channel").getJSONObject("item");
			jarray = json.getJSONArray("forecast");
			
			if (day == TODAY) {
				return json.getJSONObject("condition");
			} else {
				return jarray.getJSONObject(day);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}
	
	public static float calculateWeight() {
		return 0.0f;
	}
	
	public static void main (String args[]) {
		try {
			System.out.println(fetchWeatherInfo());//showWeatherInfo(TODAY));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
