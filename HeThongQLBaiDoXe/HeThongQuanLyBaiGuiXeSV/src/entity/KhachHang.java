package entity;

import java.util.Date;

public class KhachHang {

	private int ID_custemer;
	private String fullname;
	private String student_id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private TheXe card_id;
	private Date created_at;
	private Date updated_at;
	private ParkingHistory parkingHistory;
	
	public int getID_custemer() {
		return ID_custemer;
	}
	public void setID_custemer(int iD_custemer) {
		ID_custemer = iD_custemer;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public ParkingHistory getParkingHistory() {
		return parkingHistory;
	}
	public void setParkingHistory(ParkingHistory parkingHistory) {
		this.parkingHistory = parkingHistory;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public TheXe getCard_id() {
		return card_id;
	}
	public void setCard_id(TheXe card_id) {
		this.card_id = card_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public KhachHang(int iD_custemer, String fullname, String student_id, String username, String password,
			String email, String phone, TheXe card_id, Date created_at, Date updated_at) {
		super();
		ID_custemer = iD_custemer;
		this.fullname = fullname;
		this.student_id = student_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.card_id = card_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public KhachHang(int iD_custemer) {
		super();
		ID_custemer = iD_custemer;
	}
	public KhachHang() {
		super();
	}
	
	
	public KhachHang(String student_id, TheXe card_id, Date created_at) {
		super();
		this.student_id = student_id;
		this.card_id = card_id;
		this.created_at = created_at;
	}
	public KhachHang(String fullname, String student_id, String username, String password, String email, String phone,
			TheXe card_id, Date created_at, Date updated_at) {
		super();
		this.fullname = fullname;
		this.student_id = student_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.card_id = card_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
	public KhachHang(String text, int id, Date date) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "KhachHang [ID_custemer=" + ID_custemer + ", fullname=" + fullname + ", student_id=" + student_id
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ ", card_id=" + card_id + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
	
}
