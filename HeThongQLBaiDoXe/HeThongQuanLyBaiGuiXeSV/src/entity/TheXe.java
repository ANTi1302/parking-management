package entity;

public class TheXe {

	private int id;
	private String barcode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public TheXe(int id, String barcode) {
		super();
		this.id = id;
		this.barcode = barcode;
	}
	public TheXe() {
		super();
	}
	public TheXe(int id) {
		super();
		this.id = id;
	}
	@Override
	public String toString() {
		return "TheXe [id=" + id + ", barcode=" + barcode + "]";
	}
	
	
}
