package order;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBController;

public class OrderController {
	private static String jDateToSqlDate (Date d) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateToStr = format.format(d);
		return dateToStr;
	}
	
	public static void addToDatabase(int uid, Date d, int item1, int item2, int item3, int item4, int item5) {
		String query = "INSERT INTO data_pesanan (tanggal_penjualan, id_user) VALUE ('"
				+ jDateToSqlDate(d) + "', " + uid + ")";
		ResultSet rs = DBController.queryDatabase(query);
		Integer last_inserted_id = 0;
		try {
			if(rs.next()) {
	            last_inserted_id = rs.getInt(1);
				// INSERT PRODUK MASING2
	            String query_1 = "INSERT INTO pesanan_produk VALUE ("
						+ last_inserted_id + ", " + "'Brownies kukus original'" + ", " + new Integer(item1) + ")";
	            String query_2 = "INSERT INTO pesanan_produk VALUE ("
						+ last_inserted_id + ", " + "'Brownies kukus cream cheese'" + ", " + new Integer(item2) + ")";
	            String query_3 = "INSERT INTO pesanan_produk VALUE ("
						+ last_inserted_id + ", " + "'Brownies bakar original'" + ", " + new Integer(item3) + ")";
	            String query_4 = "INSERT INTO pesanan_produk VALUE ("
						+ last_inserted_id + ", " + "'Bolen pisang keju'" + ", " + new Integer(item4) + ")";
	            String query_5 = "INSERT INTO pesanan_produk VALUE ("
						+ last_inserted_id + ", " + "'Bolen pisang coklat'" + ", " + new Integer(item5) + ")";
	            DBController.queryDatabase(query_1);
	            DBController.queryDatabase(query_2);
	            DBController.queryDatabase(query_3);
	            DBController.queryDatabase(query_4);
	            DBController.queryDatabase(query_5);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {

	}

}
