package com.kh.app.paycancel.vo;

public class PayCancelVo {

	private String carPaymentNo;
	private String accomodationPaymentNo;
	private String souvenirPaymentNo;
	private String title;
	private String content;
	public String getCarPaymentNo() {
		return carPaymentNo;
	}
	public void setCarPaymentNo(String carPaymentNo) {
		this.carPaymentNo = carPaymentNo;
	}
	public String getAccomodationPaymentNo() {
		return accomodationPaymentNo;
	}
	public void setAccomodationPaymentNo(String accomodationPaymentNo) {
		this.accomodationPaymentNo = accomodationPaymentNo;
	}
	public String getSouvenirPaymentNo() {
		return souvenirPaymentNo;
	}
	public void setSouvenirPaymentNo(String souvenirPaymentNo) {
		this.souvenirPaymentNo = souvenirPaymentNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PayCancelVo [carPaymentNo=" + carPaymentNo + ", accomodationPaymentNo=" + accomodationPaymentNo
				+ ", souvenirPaymentNo=" + souvenirPaymentNo + ", title=" + title + ", content=" + content + "]";
	}
	public PayCancelVo(String carPaymentNo, String accomodationPaymentNo, String souvenirPaymentNo, String title,
			String content) {
		super();
		this.carPaymentNo = carPaymentNo;
		this.accomodationPaymentNo = accomodationPaymentNo;
		this.souvenirPaymentNo = souvenirPaymentNo;
		this.title = title;
		this.content = content;
	}
	public PayCancelVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
