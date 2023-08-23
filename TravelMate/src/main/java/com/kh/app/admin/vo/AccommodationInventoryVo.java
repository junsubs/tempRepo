package com.kh.app.admin.vo;

public class AccommodationInventoryVo {
	
	private String no;
	private String name;
	private String localName;
	private String kind;
	private String maxPeople;
	private String countYn;
	private String changeName;
	private String originName;
	private String price;
	
	public AccommodationInventoryVo() {
		super();
	}

	public AccommodationInventoryVo(String no, String name, String localName, String kind, String maxPeople,
			String countYn, String changeName, String originName, String price) {
		super();
		this.no = no;
		this.name = name;
		this.localName = localName;
		this.kind = kind;
		this.maxPeople = maxPeople;
		this.countYn = countYn;
		this.changeName = changeName;
		this.originName = originName;
		this.price = price;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(String maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getCountYn() {
		return countYn;
	}

	public void setCountYn(String countYn) {
		this.countYn = countYn;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AccommodationInventoryVo [no=" + no + ", name=" + name + ", localName=" + localName + ", kind=" + kind
				+ ", maxPeople=" + maxPeople + ", countYn=" + countYn + ", changeName=" + changeName + ", originName="
				+ originName + ", price=" + price + "]";
	}
	
}
