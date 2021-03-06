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

public class KhachHang_Dao {
	public ArrayList<KhachHang> gettalltbKhachHang() {
		ArrayList<KhachHang> dsNV=new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con= ConnectDB.getConnection();
			String sql="select*from [dbo].[Custemer]";
			Statement statement= con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
				int ID_custemer=rs.getInt("ID_custemer");
				String fullname=rs.getString("fullname");
				String student_id=rs.getString("student_id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				TheXe chucVu=new TheXe_Dao().getTheTheoBarcode(rs.getString("card_id"));
				Date created_at = rs.getDate("created_at");
				Date updated_at = rs.getDate("updated_at");
				String sdt=rs.getString("sdt");
				KhachHang nv=new KhachHang(ID_custemer, fullname, student_id, username, password, email, phone, chucVu, created_at, updated_at);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}
//	SELECT *
//	FROM     TheXe INNER JOIN
//	                  Custemer ON TheXe.id = Custemer.card_id
//				where Custemer.card_id=1
	public KhachHang gettalltbKhachHangTheoCard( String string) {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="SELECT *\r\n"
					+ "FROM     TheXe INNER JOIN\r\n"
					+ "                  Custemer ON TheXe.id = Custemer.card_id\r\n"
					+ "			where TheXe.barcode=?";
			statement=con.prepareStatement(sql);
			statement.setString(1, string);
			ResultSet rs=statement.executeQuery();
			 if (rs.next() == false) {
				  return kh;
			 }
			 else {
				 int ID_custemer=rs.getInt("ID_custemer");
				 String barcode=rs.getString("barcode");
					String fullname=rs.getString("fullname");
					String student_id=rs.getString("student_id");
					String username=rs.getString("username");
					String password=rs.getString("password");
					String email=rs.getString("email");
					String phone=rs.getString("phone");
					TheXe chucVu=new TheXe_Dao().getTheTheoID(rs.getString("card_id"));
					Date created_at = rs.getDate("created_at");
					Date updated_at = rs.getDate("updated_at");
					kh=new KhachHang(ID_custemer, fullname, student_id, username, password, email, phone, chucVu, created_at, updated_at);
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
	
	public KhachHang gettalltbKhachHangTheoIDCustumer() {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="select top (1) * from [dbo].[Custemer]ORDER by[ID_custemer] DESC ";
			statement=con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			 if (rs.next() == false) {
				  return kh;
			 }
			 else {
				 int ID_custemer=rs.getInt("ID_custemer");
					String fullname=rs.getString("fullname");
					String student_id=rs.getString("student_id");
					String username=rs.getString("username");
					String password=rs.getString("password");
					String email=rs.getString("email");
					String phone=rs.getString("phone");
					TheXe chucVu=new TheXe_Dao().getTheTheoID(rs.getString("card_id"));
					Date created_at = rs.getDate("created_at");
					Date updated_at = rs.getDate("updated_at");
					kh=new KhachHang(ID_custemer, fullname, student_id, username, password, email, phone, chucVu, created_at, updated_at);
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
	public boolean create(KhachHang nv) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("INSERT INTO [dbo].[Custemer]([student_id],[card_id],[created_at]) \r\n"
					+ "values(?,?,?)");
			stmt.setString(1,nv.getStudent_id());
			stmt.setInt(2,nv.getCard_id().getId());
//			stmt.setDate(9, (Date) nv.getCreated_at());
//			stmt.setDate(10,(Date) nv.getUpdated_at());
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String datecreated= simpleDateFormat.format(nv.getCreated_at());
			stmt.setString(3, datecreated);
			
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
	
//	UPDATE [dbo].[Custemer]
//			SET  [card_id]= NULL
//			WHERE [card_id]=1;
public boolean updateMaChucVu(String kh) {
		
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("UPDATE [dbo].[Custemer]\r\n"
					+ "SET  [card_id]= NULL\r\n"
					+ "WHERE [card_id]=?");
			stmt.setString(1,kh);
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
//SELECT  ID_custemer, fullname, username, email, phone,[card_id], created_at, count(*) as SoLan, [updated_at]= max( [check_out_at])
//FROM     Custemer INNER JOIN
//                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id
//		 group by ID_custemer, fullname, username, email, phone, created_at,[updated_at],[card_id]
//having EXISTS ( select *,count(*) as SoLan
//from [dbo].[ParkingHistory]
//group by [custemer_id])
public ArrayList<KhachHang> gettalltbKhachHangCoNgayRa() {
	ArrayList<KhachHang> dsNV=new ArrayList<KhachHang>();
	try {
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		String sql="SELECT  ID_custemer, fullname, username, email, phone,[card_id], created_at, count(*) as SoLan, [updated_at]= max( [check_out_at])\r\n"
				+ "FROM     Custemer INNER JOIN\r\n"
				+ "                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id\r\n"
				+ "		 group by ID_custemer, fullname, username, email, phone, created_at,[updated_at],[card_id]\r\n"
				+ "having EXISTS ( select *,count(*) as SoLan\r\n"
				+ "from [dbo].[ParkingHistory]\r\n"
				+ "group by [custemer_id])";
		Statement statement= con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		while (rs.next()) {
			int ID_custemer=rs.getInt("ID_custemer");
			String fullname=rs.getString("fullname");
			String student_id=rs.getString("username");
			String username=rs.getString("email");
			String password=rs.getString("phone");
			String phone=rs.getString("phone");
			TheXe chucVu=new TheXe_Dao().getTheTheoBarcode(rs.getString("card_id"));
			Date created_at = rs.getDate("created_at");
			Date updated_at = rs.getDate("updated_at");
			String sdt=rs.getString("SoLan");
			KhachHang nv=new KhachHang(ID_custemer, fullname, student_id, username, password, sdt, phone, chucVu, created_at, updated_at);
			dsNV.add(nv);
		}
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return dsNV;
}

}
