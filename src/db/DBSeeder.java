package db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import cashier.SalesController;
import estimation.InfoEstimation;
import weather.InfoWeather;

public class DBSeeder {
	public static void seedWeatherTable(int n) {
		DBController.connectDatabase();
		DBController.queryDatabase("DELETE FROM info_cuaca");
		
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, -n);
        d = cal.getTime();
        
        for (int i = 0; i < n; i++) {
			Random rnd = new Random();
			int rnd_int = rnd.nextInt(10);
			String weather = "Sunny";
			if (0 <= rnd_int && rnd_int <= 1) {
				weather = "Rain";
			} else if (2 <= rnd_int && rnd_int <= 5) {
				weather = "Sunny";
			} else if (6 <= rnd_int && rnd_int <= 8) {
				weather = "Cloudy";
			} else {
				weather = "Thunderstorm";
			}
			
			InfoWeather.addDatabase(d, weather);
			
			cal.add(Calendar.DATE, 1); d = cal.getTime();
		}
		DBController.closeDatabase();
	}
	
	public static void seedSalesEstimationTable(int n) {
		DBController.connectDatabase();
		DBController.queryDatabase("DELETE FROM penjualan_produk");
		DBController.queryDatabase("DELETE FROM data_penjualan");
		
		DBController.queryDatabase("DELETE FROM data_estimasi");
		DBController.queryDatabase("DELETE FROM estimasi_produk");
		
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, -n);
        d = cal.getTime();
        
		for (int i = 0; i < n; i++) {
			String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(d);
			
			double multiplier = 1.0;
			if (day.equals("Sunday") || day.equals("Saturday")) {
				multiplier = 0.9;
			} else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday")) {
				multiplier = 0.5;
			} else {
				multiplier = 0.7;
			}
			
			Random rnd = new Random();
			
			/* Sales Table */
			int item1 = (int) Math.ceil(multiplier * (rnd.nextInt(250) + 150));
			int item2 = (int) Math.ceil(multiplier * (rnd.nextInt(250) + 50));
			int item3 = (int) Math.ceil(multiplier * (rnd.nextInt(100)));
			int item4 = (int) Math.ceil(multiplier * (rnd.nextInt(25) + 25));
			int item5 = (int) Math.ceil(multiplier * (rnd.nextInt(25) + 25));
			
			SalesController.addToDatabase(0, d, item1, item2, item3, item4, item5);
			
			/* Estimation Table */
			item1 += (rnd.nextInt(81) - 40);
			item2 += (rnd.nextInt(81) - 40);
			item3 += (rnd.nextInt(51) - 25);
			item4 += (rnd.nextInt(21) - 10);
			item5 += (rnd.nextInt(21) - 10);
			
			InfoEstimation.addToDatabase(0, d, item1, item2, item3, item4, item5);
			
			cal.add(Calendar.DATE, 1); d = cal.getTime();
		}
		DBController.closeDatabase();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		seedSalesEstimationTable(100);
		seedWeatherTable(100);
		System.out.println("DONE SEEDING!");
	}

}
