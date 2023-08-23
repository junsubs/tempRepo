package com.kh.app.cs.vo;

public class FAQVo {
	
	private String no;
	private String memberNo;
	private String title;
	private String answer;
	private String deleteYn;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	@Override
	public String toString() {
		return "FAQVo [no=" + no + ", memberNo=" + memberNo + ", title=" + title + ", answer=" + answer + ", deleteYn="
				+ deleteYn + "]";
	}
	public FAQVo(String no, String memberNo, String title, String answer, String deleteYn) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.title = title;
		this.answer = answer;
		this.deleteYn = deleteYn;
	}
	public FAQVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
