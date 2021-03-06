package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.TheXe;

public class TheXe_Dao {

	public ArrayList<TheXe> gettalltbThe() {
		ArrayList<TheXe> dsNV = new ArrayList<TheXe>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from TheXe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maNV = rs.getInt("id");
				String ten = rs.getString("barcode");
				TheXe nv = new TheXe(maNV, ten);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}

	public TheXe getTheTheoBarcode(String ma) {
		TheXe nv = null;

		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from TheXe where barcode = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return nv;
			} else {
				int id = rs.getInt("id");
				String bar = rs.getString("barcode");
				nv = new TheXe(id, bar);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return nv;
	}
	
	public TheXe getTheTheoID(String ma) {
		TheXe nv = null;

		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from TheXe where id = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return nv;
			} else {
				int id = rs.getInt("id");
				String bar = rs.getString("barcode");
				nv = new TheXe(id, bar);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return nv;
	}
	
}
