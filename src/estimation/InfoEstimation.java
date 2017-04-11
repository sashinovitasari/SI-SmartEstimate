package estimation;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import db.DBController;
import weka.core.Instance;

public class InfoEstimation {
	public static void addToDatabase() {
		
	}
	
	public static void fetchData(int product_id) {
		String query = "";
		switch (product_id) {
		case 1:
			query = "SELECT * FROM data_penjualan NATURAL JOIN penjualan_produk WHERE nama_produk = 'Brownies kukus original'";
			break;
		case 2:
			query = "SELECT * FROM data_penjualan NATURAL JOIN penjualan_produk WHERE nama_produk = 'Brownies kukus cream cheese'";
			break;
		case 3:
			query = "SELECT * FROM data_penjualan NATURAL JOIN penjualan_produk WHERE nama_produk = 'Brownies bakar original'";
			break;
		case 4:
			query = "SELECT * FROM data_penjualan NATURAL JOIN penjualan_produk WHERE nama_produk = 'Bolen pisang keju'";
			break;
		case 5:
			query = "SELECT * FROM data_penjualan NATURAL JOIN penjualan_produk WHERE nama_produk = 'Bolen pisang coklat'";
			break;
		}
		
		ResultSet rs = DBController.queryDatabase(query);
		
		ProcEstimation.initializeInstances();
		
		try {
			while(rs.next()) {
				Date d = rs.getDate("tanggal_penjualan");
				Calendar cal = Calendar.getInstance();
	            cal.setTime(d);
	            
				String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(d);
				System.out.println(day);
				int date = cal.get(Calendar.DATE);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				String weather = "Sunny";
				int sales = rs.getInt("jumlah_penjualan");
				
				Instance inst = ProcEstimation.createInstance(day, date, month, year, weather, sales);
				ProcEstimation.data.add(inst);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String args[]) {
		fetchData(1);
		System.out.println(ProcEstimation.calculateEstimation());
		
	}
}
