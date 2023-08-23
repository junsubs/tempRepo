package com.kh.app.mypage.vo;

public class OrderListVo {
	
	private String no;
	private String payNo;
	private String payType;
	private String payDate;
	private String img;
	private String name;
	private String startDate;
	private String endDate;
	private String price;
	private String cancelYn;
	
	private String address;
	private String oderName;
	private String cnt;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOderName() {
		return oderName;
	}
	public void setOderName(String oderName) {
		this.oderName = oderName;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "OrderListVo [no=" + no + ", payNo=" + payNo + ", payType=" + payType + ", payDate=" + payDate + ", img="
				+ img + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price
				+ ", cancelYn=" + cancelYn + ", address=" + address + ", oderName=" + oderName + ", cnt=" + cnt + "]";
	}
	public OrderListVo(String no, String payNo, String payType, String payDate, String img, String name,
			String startDate, String endDate, String price, String cancelYn, String address, String oderName,
			String cnt) {
		super();
		this.no = no;
		this.payNo = payNo;
		this.payType = payType;
		this.payDate = payDate;
		this.img = img;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.cancelYn = cancelYn;
		this.address = address;
		this.oderName = oderName;
		this.cnt = cnt;
	}
	public OrderListVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

}
