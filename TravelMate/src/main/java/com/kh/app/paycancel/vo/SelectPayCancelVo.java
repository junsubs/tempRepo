package com.kh.app.paycancel.vo;

public class SelectPayCancelVo {
	private String payNo;
	private String price;
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "SelectPayCancelVo [payNo=" + payNo + ", price=" + price + "]";
	}
	public SelectPayCancelVo(String payNo, String price) {
		super();
		this.payNo = payNo;
		this.price = price;
	}
	public SelectPayCancelVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
