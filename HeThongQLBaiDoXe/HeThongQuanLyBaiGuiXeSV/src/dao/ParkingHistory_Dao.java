package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.ParkingHistory;
import entity.TheXe;

public class ParkingHistory_Dao {

	public boolean create(ParkingHistory pr) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("\r\n"
					+ "INSERT [dbo].[ParkingHistory] ([license_plate],[check_in_at],[price],[custemer_id],[img]) \r\n"
					+ "VALUES (?,?,?,?,?)");
			stmt.setString(1,pr.getLicense_plate());
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String check_in_at= simpleDateFormat.format(pr.getCheck_in_at());
//			String check_out_at= simpleDateFormat.format(pr.getCheck_out_at());
			stmt.setString(2, check_in_at);
//			stmt.setString(3, check_out_at);
			stmt.setDouble(3,pr.getPrice());
			stmt.setInt(4, pr.getKhachHang().getID_custemer());
			stmt.setString(5, pr.getImg());
			
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}return n>0;
	}
	
//	SELECT  ParkingHistory.license_plate, ParkingHistory.check_in_at, ParkingHistory.check_out_at, TheXe.barcode, ParkingHistory.img
//	FROM     Custemer INNER JOIN
//	                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id INNER JOIN
//	                  TheXe ON Custemer.card_id = TheXe.id
//	GROUP BY Custemer.ID_custemer, Custemer.fullname, ParkingHistory.license_plate, ParkingHistory.check_in_at, ParkingHistory.check_out_at, TheXe.barcode, ParkingHistory.img
//	having TheXe.barcode=04894957
	public ParkingHistory gettalltbParkingHistoryTheoCard( String string) {
		ParkingHistory kh = null;
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="SELECT  ParkingHistory.license_plate, ParkingHistory.check_in_at, ParkingHistory.check_out_at, ParkingHistory.img\r\n"
					+ "FROM     Custemer INNER JOIN\r\n"
					+ "                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id INNER JOIN\r\n"
					+ "                  TheXe ON Custemer.card_id = TheXe.id\r\n"
					+ "GROUP BY Custemer.ID_custemer, Custemer.fullname, ParkingHistory.license_plate, ParkingHistory.check_in_at, ParkingHistory.check_out_at, TheXe.barcode, ParkingHistory.img\r\n"
					+ "having TheXe.barcode=?";
			statement=con.prepareStatement(sql);
			statement.setString(1, string);
			ResultSet rs=statement.executeQuery();
			 if (rs.next() == false) {
				  return kh;
			 }
			 else {
					String license_plate=rs.getString("license_plate");
					Date check_in_at = rs.getDate("check_in_at");
					Date check_out_at = rs.getDate("check_out_at");
					String img=rs.getString("img");
					kh=new ParkingHistory(license_plate, check_in_at, check_out_at, img);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}return kh;
	
	}
	
//	UPDATE [dbo].[ParkingHistory]
//			SET [check_out_at] = '2/12/2022'
//			WHERE EXISTS(
//
//			SELECT [license_plate],[check_in_at],[check_out_at]
//			FROM     Custemer INNER JOIN
//			                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id INNER JOIN
//			                  TheXe ON Custemer.card_id = TheXe.id
//					where TheXe.barcode='04894957'
//							  )
public boolean updateTimeOut(String barco) {
		
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("UPDATE [dbo].[ParkingHistory]\r\n"
					+ "SET [check_out_at] = GETDATE()\r\n"
					+ "WHERE EXISTS(\r\n"
					+ "\r\n"
					+ "SELECT [license_plate],[check_in_at],[check_out_at]\r\n"
					+ "FROM     Custemer INNER JOIN\r\n"
					+ "                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id INNER JOIN\r\n"
					+ "                  TheXe ON Custemer.card_id = TheXe.id\r\n"
					+ "		where TheXe.barcode=?\r\n"
					+ "				  )");
			stmt.setString(1,barco);
			n=stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}return n>0;
	}

}
