package com.kh.app.admin.vo;

public class SellRequestVo {
	
	private String no;
	private String title;
	private String writer;
	private String enrollDate;
	private String img;
	
	public SellRequestVo() {
		super();
	}

	public SellRequestVo(String no, String title, String writer, String enrollDate, String img) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.enrollDate = enrollDate;
		this.img = img;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "SellRequestVo [no=" + no + ", title=" + title + ", writer=" + writer + ", enrollDate=" + enrollDate
				+ ", img=" + img + "]";
	}
	
}
