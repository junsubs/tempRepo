package com.kh.app.mypage.vo;

public class AttendanceVo {

	private String date;
	private String no;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "AttendanceVo [date=" + date + ", no=" + no + "]";
	}
	public AttendanceVo(String date, String no) {
		super();
		this.date = date;
		this.no = no;
	}
	public AttendanceVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
