package app;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import dao.ParkingHistory_Dao;
import dao.TheXe_Dao;
import entity.KhachHang;
import entity.ParkingHistory;

public class demo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectDB.getInstance().connect();
		KhachHang_Dao khachHang_Dao= new KhachHang_Dao();
		ParkingHistory_Dao parkingHistory_Dao= new ParkingHistory_Dao();
//		System.out.println(khachHang_Dao.gettalltbKhachHangTheoCard("04894957"));
//		TheXe_Dao theXe_Dao= new TheXe_Dao();
//		System.out.println(parkingHistory_Dao.gettalltbParkingHistoryTheoCard("04894957"));
//		KhachHang kh= khachHang_Dao.gettalltbKhachHangTheoIDCustumer(4);
//		System.out.println(khachHang_Dao.gettalltbKhachHangTheoIDCustumer(4));
//		ParkingHistory pr=new ParkingHistory("988-12", new Date(), 5, khachHang_Dao.gettalltbKhachHangTheoIDCustumer());
//		if (parkingHistory_Dao.create(pr)) {
//			System.out.println("ok");
//		}else {
//			System.out.println("nooo");
//		}
//		if (khachHang_Dao.updateMaChucVu("1")) {
//			System.out.println("okk");
//		}else {
//			System.out.println("noooo");
//		}
//		if (parkingHistory_Dao.updateTimeOut("04894957")) {
//			System.out.println("okk");
//		}else {
//			System.out.println("noooo");
//		}
		
		System.out.println(khachHang_Dao.gettalltbKhachHangCoNgayRa());
		
		
	}

}
