package com.kh.app.cs.vo;

public class InqueryVo {

	private String no;
	private String memberNo;
	private String title;
	private String content;
	private String enrollDate;
	private String deleteYn;
	
	private String memberNick;

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

	@Override
	public String toString() {
		return "InqueryVo [no=" + no + ", memberNo=" + memberNo + ", title=" + title + ", content=" + content
				+ ", enrollDate=" + enrollDate + ", deleteYn=" + deleteYn + ", memberNick=" + memberNick + "]";
	}

	public InqueryVo(String no, String memberNo, String title, String content, String enrollDate, String deleteYn,
			String memberNick) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.deleteYn = deleteYn;
		this.memberNick = memberNick;
	}

	public InqueryVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
