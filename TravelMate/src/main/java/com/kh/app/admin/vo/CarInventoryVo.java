package com.kh.app.admin.vo;

public class CarInventoryVo {

	private String no;
	private String kind;
	private String count;
	private String name;
	private String max;
	private String licensePlate;
	private String price;
	private String licenseDate;
	private String changeName;
	private String originName;
	
	
	public CarInventoryVo() {
		super();
	}


	public CarInventoryVo(String no, String kind, String count, String name, String max, String licensePlate,
			String price, String licenseDate, String changeName, String originName) {
		super();
		this.no = no;
		this.kind = kind;
		this.count = count;
		this.name = name;
		this.max = max;
		this.licensePlate = licensePlate;
		this.price = price;
		this.licenseDate = licenseDate;
		this.changeName = changeName;
		this.originName = originName;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMax() {
		return max;
	}


	public void setMax(String max) {
		this.max = max;
	}


	public String getLicensePlate() {
		return licensePlate;
	}


	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getLicenseDate() {
		return licenseDate;
	}


	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}


	public String getChangeName() {
		return changeName;
	}


	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	@Override
	public String toString() {
		return "CarInventoryVo [no=" + no + ", kind=" + kind + ", count=" + count + ", name=" + name + ", max=" + max
				+ ", licensePlate=" + licensePlate + ", price=" + price + ", licenseDate=" + licenseDate
				+ ", changeName=" + changeName + ", originName=" + originName + "]";
	}

}
