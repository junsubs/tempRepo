package com.kh.app.mypage.vo;

public class FavoriteVo {
	
	private String no;
	private String memberNo;
	private String carNo;
	private String accomodationNo;
	private String souvenirNo;
	private String delYn;
	
	private String carKind;
	private String carImg;
	
	private String accomodationName;
	private String accomodationImg;
	
	private String souvenirName;
	private String souvenirPrice;
	private String souvenirImg;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getAccomodationNo() {
		return accomodationNo;
	}
	public void setAccomodationNo(String accomodationNo) {
		this.accomodationNo = accomodationNo;
	}
	public String getSouvenirNo() {
		return souvenirNo;
	}
	public void setSouvenirNo(String souvenirNo) {
		this.souvenirNo = souvenirNo;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCarKind() {
		return carKind;
	}
	public void setCarKind(String carKind) {
		this.carKind = carKind;
	}
	public String getCarImg() {
		return carImg;
	}
	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}
	public String getAccomodationName() {
		return accomodationName;
	}
	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}
	public String getAccomodationImg() {
		return accomodationImg;
	}
	public void setAccomodationImg(String accomodationImg) {
		this.accomodationImg = accomodationImg;
	}
	public String getSouvenirName() {
		return souvenirName;
	}
	public void setSouvenirName(String souvenirName) {
		this.souvenirName = souvenirName;
	}
	public String getSouvenirPrice() {
		return souvenirPrice;
	}
	public void setSouvenirPrice(String souvenirPrice) {
		this.souvenirPrice = souvenirPrice;
	}
	public String getSouvenirImg() {
		return souvenirImg;
	}
	public void setSouvenirImg(String souvenirImg) {
		this.souvenirImg = souvenirImg;
	}
	@Override
	public String toString() {
		return "FavoriteVo [no=" + no + ", memberNo=" + memberNo + ", carNo=" + carNo + ", accomodationNo="
				+ accomodationNo + ", souvenirNo=" + souvenirNo + ", delYn=" + delYn + ", carKind=" + carKind
				+ ", carImg=" + carImg + ", accomodationName=" + accomodationName + ", accomodationImg="
				+ accomodationImg + ", souvenirName=" + souvenirName + ", souvenirPrice=" + souvenirPrice
				+ ", souvenirImg=" + souvenirImg + "]";
	}
	public FavoriteVo(String no, String memberNo, String carNo, String accomodationNo, String souvenirNo, String delYn,
			String carKind, String carImg, String accomodationName, String accomodationImg, String souvenirName,
			String souvenirPrice, String souvenirImg) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.carNo = carNo;
		this.accomodationNo = accomodationNo;
		this.souvenirNo = souvenirNo;
		this.delYn = delYn;
		this.carKind = carKind;
		this.carImg = carImg;
		this.accomodationName = accomodationName;
		this.accomodationImg = accomodationImg;
		this.souvenirName = souvenirName;
		this.souvenirPrice = souvenirPrice;
		this.souvenirImg = souvenirImg;
	}
	public FavoriteVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
}
