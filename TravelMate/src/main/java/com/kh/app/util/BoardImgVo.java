package com.kh.app.util;

public class BoardImgVo {
	
	private String no;
	private String bno;
	private String title;
	private String changeName;
	private String enrollDate;
	private String status;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BoardImgVo [no=" + no + ", bno=" + bno + ", title=" + title + ", changeName=" + changeName
				+ ", enrollDate=" + enrollDate + ", status=" + status + "]";
	}
	public BoardImgVo(String no, String bno, String title, String changeName, String enrollDate, String status) {
		super();
		this.no = no;
		this.bno = bno;
		this.title = title;
		this.changeName = changeName;
		this.enrollDate = enrollDate;
		this.status = status;
	}
	public BoardImgVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}