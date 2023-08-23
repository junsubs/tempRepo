package com.kh.app.cs.vo;

public class InqueryReplyVo {
	
	private String no;
	private String memberNo;
	private String qnaNo;
	private String content;
	private String enrollDate;
	private String modifyDate;
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
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
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
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	@Override
	public String toString() {
		return "InqueryReplyVo [no=" + no + ", memberNo=" + memberNo + ", qnaNo=" + qnaNo + ", content=" + content
				+ ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", deleteYn=" + deleteYn + "]";
	}
	public InqueryReplyVo(String no, String memberNo, String qnaNo, String content, String enrollDate,
			String modifyDate, String deleteYn) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.qnaNo = qnaNo;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteYn = deleteYn;
	}
	public InqueryReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
