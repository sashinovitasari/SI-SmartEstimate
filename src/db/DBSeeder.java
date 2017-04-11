package db;

import java.util.Calendar;
import java.util.Date;
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
			Random rnd = new Random();
			int item1 = rnd.nextInt(250) + 150;
			int item2 = rnd.nextInt(250) + 50;
			int item3 = rnd.nextInt(250);
			int item4 = rnd.nextInt(100) + 25;
			int item5 = rnd.nextInt(100) + 25;
			
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
