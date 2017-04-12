package estimation;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import db.DBController;
import weather.InfoWeather;
import weka.core.Instance;

public class InfoEstimation {
	private static String jDateToSqlDate (Date d) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateToStr = format.format(d);
		return dateToStr;
	}
	
	public static boolean alreadyInDatabase (Date d) {
		boolean result = false;
		String query = "SELECT * FROM data_estimasi WHERE tanggal_penjualan = '" + jDateToSqlDate(d) +"'";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		try {
			result = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBController.closeDatabase();
		}
		return result;
	}
	
	public static void addToDatabase(int uid, Date d, int item1, int item2, int item3, int item4, int item5) {
		String query = "INSERT INTO data_estimasi (tanggal_penjualan, id_user) VALUE ('"
				+ jDateToSqlDate(d) + "', " + uid + ")";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		Integer last_inserted_id = 0;
		try {
			if(rs.next()) {
	            last_inserted_id = rs.getInt(1);
				// INSERT PRODUK MASING2
	            String query_1 = "INSERT INTO estimasi_produk VALUE ("
						+ last_inserted_id + ", " + "'Brownies kukus original'" + ", " + new Integer(item1) + ")";
	            String query_2 = "INSERT INTO estimasi_produk VALUE ("
						+ last_inserted_id + ", " + "'Brownies kukus cream cheese'" + ", " + new Integer(item2) + ")";
	            String query_3 = "INSERT INTO estimasi_produk VALUE ("
						+ last_inserted_id + ", " + "'Brownies bakar original'" + ", " + new Integer(item3) + ")";
	            String query_4 = "INSERT INTO estimasi_produk VALUE ("
						+ last_inserted_id + ", " + "'Bolen pisang keju'" + ", " + new Integer(item4) + ")";
	            String query_5 = "INSERT INTO estimasi_produk VALUE ("
						+ last_inserted_id + ", " + "'Bolen pisang coklat'" + ", " + new Integer(item5) + ")";
	            DBController.queryDatabase(query_1);
	            DBController.queryDatabase(query_2);
	            DBController.queryDatabase(query_3);
	            DBController.queryDatabase(query_4);
	            DBController.queryDatabase(query_5);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBController.closeDatabase();
		}
	}
	
	public static double calculateAverageAccuracy() {
		double result = 85.0;
		String query = "SELECT avg(akurasi) AS rata2 FROM ("
				+ "SELECT *, 1 - (ABS(jumlah_estimasi-jumlah_penjualan)/jumlah_penjualan) AS akurasi FROM ("
				+ "SELECT tanggal_penjualan, nama_produk, jumlah_penjualan FROM data_penjualan NATURAL JOIN penjualan_produk"
				+ ") AS sales NATURAL JOIN ("
				+ "SELECT tanggal_penjualan, nama_produk, jumlah_estimasi FROM data_estimasi NATURAL JOIN estimasi_produk"
				+ ") AS estimasi"
				+ ") AS besar";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		try {
			if (rs.next()) {
				result = rs.getDouble("rata2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBController.closeDatabase();
		}
		return result;
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
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		
		ProcEstimation.initializeInstances();
		
		try {
			while(rs.next()) {
				Date d = rs.getDate("tanggal_penjualan");
				Calendar cal = Calendar.getInstance();
	            cal.setTime(d);
	            
				String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(d);
				int date = cal.get(Calendar.DATE);
				int month = cal.get(Calendar.MONTH) + 1;
				int year = cal.get(Calendar.YEAR);
				String weather = InfoWeather.infoWeatherFromDatabase(d);
				int sales = rs.getInt("jumlah_penjualan");
				
				Instance inst = ProcEstimation.createInstance(day, date, month, year, weather, sales);
				ProcEstimation.data.add(inst);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBController.closeDatabase();
		}
		ProcEstimation.neuralModel = ProcEstimation.generateEstimationModel();
	}
	
	public static void main (String args[]) {
		System.out.println(calculateAverageAccuracy());
	}
}
