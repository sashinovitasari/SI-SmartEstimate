package db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import cashier.SalesController;

public class DBSeeder {
	public static void seedSalesTable(int n) {
		DBController.connectDatabase();
		DBController.queryDatabase("DELETE FROM penjualan_produk");
		DBController.queryDatabase("DELETE FROM data_penjualan");
		
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
			int item1 = (int) Math.ceil(multiplier * (rnd.nextInt(250) + 150));
			int item2 = (int) Math.ceil(multiplier * (rnd.nextInt(250) + 50));
			int item3 = (int) Math.ceil(multiplier * (rnd.nextInt(100)));
			int item4 = (int) Math.ceil(multiplier * (rnd.nextInt(25) + 25));
			int item5 = (int) Math.ceil(multiplier * (rnd.nextInt(25) + 25));
			
			SalesController.addToDatabase(0, d, item1, item2, item3, item4, item5);
			
			cal.add(Calendar.DATE, 1); d = cal.getTime();
		}
		DBController.closeDatabase();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		seedSalesTable(100);
	}

}
