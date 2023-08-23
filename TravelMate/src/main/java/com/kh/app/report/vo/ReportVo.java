package com.kh.app.report.vo;

public class ReportVo {
	
	private String no;
	private String memberNo;
	private String boardNo;
	private String sanctionReasonNo;
	private String content;
	public ReportVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportVo(String no, String memberNo, String boardNo, String sanctionReasonNo, String content) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.sanctionReasonNo = sanctionReasonNo;
		this.content = content;
	}
	@Override
	public String toString() {
		return "ReportVo [no=" + no + ", memberNo=" + memberNo + ", boardNo=" + boardNo + ", sanctionReasonNo="
				+ sanctionReasonNo + ", content=" + content + "]";
	}
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
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getSanctionReasonNo() {
		return sanctionReasonNo;
	}
	public void setSanctionReasonNo(String sanctionReasonNo) {
		this.sanctionReasonNo = sanctionReasonNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
