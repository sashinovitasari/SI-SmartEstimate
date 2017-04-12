package news;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.omg.CORBA.portable.InputStream;
import db.DBController;

import sun.net.www.protocol.http.HttpURLConnection;

public class InfoNews {
		
	public static JSONArray fetchNewsInfo() throws Exception {
		String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
		String query = "select * from rss where url='http://jabar.tribunnews.com/rss' and description like '%bandung%'";
		String fullUrlStr = baseUrl + URLEncoder.encode(query, "UTF-8") + "&format=json";

		URL fullUrl = new URL(fullUrlStr);
		HttpURLConnection is = (HttpURLConnection) fullUrl.openConnection();
		is.connect();
		
		BufferedInputStream in = new BufferedInputStream(is.getInputStream());

		JSONTokener tok = new JSONTokener(in);
		JSONObject result = new JSONObject(tok);
		
		is.disconnect();
		
		return result.getJSONObject("query").getJSONObject("results").getJSONArray("item");
	}
	
	public static void addDatabase() {
		DBController.connectDatabase();
		ResultSet resAll = DBController.queryDatabase("SELECT count(*) as jml FROM Info_Berita");
		
		int idx=0;
		try {
			while (resAll.next()) idx = resAll.getInt("jml");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DBController.connectDatabase();
		
		try {
			JSONArray jarray = fetchNewsInfo();
			for (int i=0; i<jarray.length();i++){
				idx++;
				String query = "INSERT INTO Info_Berita (id_berita,deskripsi,kategori,tanggal_berita, bobot_berita, lokasi,url_berita) VALUES ("+idx+",\""+
								getDesc(jarray,i)+"\",\""+getTitle(jarray,i)+"\", NOW(),0,\"Bandung\",\""+getNewsURL(jarray,i)+"\")";
				
				ResultSet rs = DBController.queryDatabase(query);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBController.closeDatabase();
	}
	
	public static ResultSet showNewsInfo() {
		ResultSet resAll = DBController.queryDatabase("SELECT * FROM Info_Berita WHERE date(tanggal_berita) = CURDATE()");
		return resAll;
	}
	
	public static String descriptionParser(String desc){
		String parsedDesc = "";
		int i=0;
		while (i<desc.length() && desc.charAt(i)!='>') i++;
		
		if (i!=desc.length()){
			i++;
			int num = i;
			while (i<desc.length() && i-num<299){
				parsedDesc+= desc.charAt(i);
				i++;
			}
		}
		return parsedDesc;
	}
	
	public static String imageURLParser(String desc){
		String parsedDesc = "";
		int i=0;
		while (i<desc.length() && desc.charAt(i)!='>') i++;
		
		if (i!=desc.length()){
			i++;
			while (i<desc.length()){
				parsedDesc+= desc.charAt(i);
				i++;
			}
		}
		return parsedDesc;
		
	}
	
	public static String getTitle(JSONArray jarray, int idx){
		String title = jarray.getJSONObject(idx).getString("title");
		String parsedTitle = "";
		for (int i=0;i<28;i++){
			parsedTitle += title.charAt(i);
		}
		return parsedTitle;
	}
	
	public static String getDesc(JSONArray jarray, int idx){
		String  desc = jarray.getJSONObject(idx).getString("description");
		return descriptionParser(desc);
	}
		
	public static String getNewsURL(JSONArray jarray, int idx){	
		String url = jarray.getJSONObject(idx).getString("link");
		return url;
	}
	
	public static float calculateWeight() {
		return 0.0f;
	}
	
	public static void main (String args[]) {
		try {
			addDatabase();
			//JSONArray jarray = fetchNewsInfo();
			 //System.out.print(getNewsURL(jarray,0));
			
			//addDatabase();
			/*JSONArray newsList= showNewsInfo();
	        String desc = newsList.getJSONObject(1).getString("description");
	        System.out.print(descriptionParser(desc));(*/
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}