package weather;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.omg.CORBA.portable.InputStream;

import db.DBController;
import sun.net.www.protocol.http.HttpURLConnection;

public class InfoWeather {
	public static int TODAY = 0;
	public static int TOMORROW = 1;
	public static int NEXT_OF_TOMORROW = 2;
	public static int NEXT_NEXT_OF_TOMORROW = 3;
	
	private static String jDateToSqlDate (Date d) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateToStr = format.format(d);
		return dateToStr;
	}
	
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
	
	public static void addDatabase(Date d, String weather) {
		boolean result = false;
		String query = "SELECT * FROM info_cuaca WHERE tanggal = '" + jDateToSqlDate(d) +"'";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		try {
			result = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String updateQuery;
		if (result) { // ada di database -> Update
			updateQuery = "UPDATE info_cuaca SET nama_cuaca = '" + weather + "'"
					+ " WHERE tanggal = '" + jDateToSqlDate(d) +"'";
		} else { // ga ada di database -> Insert
			updateQuery = "INSERT INTO info_cuaca(tanggal, nama_cuaca, bobot_cuaca) VALUE ('"
					+ jDateToSqlDate(d) +"', '" + weather + "', " + "0" + ")";
		}
		rs = DBController.queryDatabase(updateQuery);
		DBController.closeDatabase();
	}
	
	public static JSONObject showWeatherInfo(int day) {
		JSONObject json = new JSONObject();
		JSONArray jarray;
		String weather = "Undefined";
		try {
			json = fetchWeatherInfo();
			json = json.getJSONObject("query").getJSONObject("results")
					.getJSONObject("channel").getJSONObject("item");
			jarray = json.getJSONArray("forecast");
			
			json = (day == TODAY) ? json.getJSONObject("condition") : jarray.getJSONObject(day);
			weather = json.getString("text");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* Insert to DB */
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, day);
        d = cal.getTime();
        addDatabase (d, weather);
		
		return json;
	}
	
	public static float calculateWeight() {
		return 0.0f;
	}
	
	public static void main (String args[]) {
		try {
			System.out.println(showWeatherInfo(TODAY));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
