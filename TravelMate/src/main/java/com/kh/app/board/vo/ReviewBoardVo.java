package com.kh.app.board.vo;

public class ReviewBoardVo {

	
	private String no;
	private String carPayNo;
	private String suvenirPayNo;
	private String roomPayNo;
	private String payType;
	private String payDate;
	private String carImg;
	private String suvenirImg;
	private String roomImg;
	private String name;
	private String oderName;
	private String title;
	private String content;
	private String enrollDate;
	private String deleteYn;
	private String memberNick;
	private String memberNo;
	public ReviewBoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ReviewBoardVo [no=" + no + ", carPayNo=" + carPayNo + ", suvenirPayNo=" + suvenirPayNo + ", roomPayNo="
				+ roomPayNo + ", payType=" + payType + ", payDate=" + payDate + ", carImg=" + carImg + ", suvenirImg="
				+ suvenirImg + ", roomImg=" + roomImg + ", name=" + name + ", oderName=" + oderName + ", title=" + title
				+ ", content=" + content + ", enrollDate=" + enrollDate + ", deleteYn=" + deleteYn + ", memberNick="
				+ memberNick + ", memberNo=" + memberNo + "]";
	}
	public ReviewBoardVo(String no, String carPayNo, String suvenirPayNo, String roomPayNo, String payType,
			String payDate, String carImg, String suvenirImg, String roomImg, String name, String oderName,
			String title, String content, String enrollDate, String deleteYn, String memberNick, String memberNo) {
		super();
		this.no = no;
		this.carPayNo = carPayNo;
		this.suvenirPayNo = suvenirPayNo;
		this.roomPayNo = roomPayNo;
		this.payType = payType;
		this.payDate = payDate;
		this.carImg = carImg;
		this.suvenirImg = suvenirImg;
		this.roomImg = roomImg;
		this.name = name;
		this.oderName = oderName;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.deleteYn = deleteYn;
		this.memberNick = memberNick;
		this.memberNo = memberNo;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCarPayNo() {
		return carPayNo;
	}
	public void setCarPayNo(String carPayNo) {
		this.carPayNo = carPayNo;
	}
	public String getSuvenirPayNo() {
		return suvenirPayNo;
	}
	public void setSuvenirPayNo(String suvenirPayNo) {
		this.suvenirPayNo = suvenirPayNo;
	}
	public String getRoomPayNo() {
		return roomPayNo;
	}
	public void setRoomPayNo(String roomPayNo) {
		this.roomPayNo = roomPayNo;
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
	public String getCarImg() {
		return carImg;
	}
	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}
	public String getSuvenirImg() {
		return suvenirImg;
	}
	public void setSuvenirImg(String suvenirImg) {
		this.suvenirImg = suvenirImg;
	}
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOderName() {
		return oderName;
	}
	public void setOderName(String oderName) {
		this.oderName = oderName;
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
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	
	
}
