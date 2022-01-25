package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.TheXe;

public class KhachHang_Dao {

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
}
