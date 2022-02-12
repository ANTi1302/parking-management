package entity;

import java.util.Date;

public class ParkingHistory {

	private int ID_parking;
	private String license_plate;
	private Date check_in_at;
	private Date check_out_at;
	private double price;
	private KhachHang khachHang;
	private String img;
	public int getID_parking() {
		return ID_parking;
	}
	public void setID_parking(int iD_parking) {
		ID_parking = iD_parking;
	}
	public String getLicense_plate() {
		return license_plate;
	}
	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}
	public Date getCheck_in_at() {
		return check_in_at;
	}
	public void setCheck_in_at(Date check_in_at) {
		this.check_in_at = check_in_at;
	}
	public Date getCheck_out_at() {
		return check_out_at;
	}
	public void setCheck_out_at(Date check_out_at) {
		this.check_out_at = check_out_at;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ParkingHistory(int iD_parking, String license_plate, Date check_in_at, Date check_out_at, double price,
			KhachHang khachHang) {
		super();
		ID_parking = iD_parking;
		this.license_plate = license_plate;
		this.check_in_at = check_in_at;
		this.check_out_at = check_out_at;
		this.price = price;
		this.khachHang = khachHang;
	}
	public ParkingHistory() {
		super();
	}
	
	public ParkingHistory(String license_plate, Date check_in_at, double price, KhachHang khachHang) {
		super();
		this.license_plate = license_plate;
		this.check_in_at = check_in_at;
		this.price = price;
		this.khachHang = khachHang;
	}
	public ParkingHistory(String license_plate, Date check_in_at, Date check_out_at, double price,
			KhachHang khachHang) {
		super();
		this.license_plate = license_plate;
		this.check_in_at = check_in_at;
		this.check_out_at = check_out_at;
		this.price = price;
		this.khachHang = khachHang;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public ParkingHistory(String license_plate, Date check_in_at, double price, KhachHang khachHang, String img) {
		super();
		this.license_plate = license_plate;
		this.check_in_at = check_in_at;
		this.price = price;
		this.khachHang = khachHang;
		this.img = img;
	}
	
	
	public ParkingHistory(String license_plate, Date check_in_at, Date check_out_at, String img) {
		super();
		this.license_plate = license_plate;
		this.check_in_at = check_in_at;
		this.check_out_at = check_out_at;
		this.img = img;
	}
	@Override
	public String toString() {
		return "ParkingHistory [ID_parking=" + ID_parking + ", license_plate=" + license_plate + ", check_in_at="
				+ check_in_at + ", check_out_at=" + check_out_at + ", price=" + price + ", khachHang=" + khachHang
				+ ", img=" + img + "]";
	}
	
	
	
}
