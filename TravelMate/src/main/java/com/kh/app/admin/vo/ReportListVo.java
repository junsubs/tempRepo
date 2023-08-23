package com.kh.app.admin.vo;

public class ReportListVo {
	
	private String no;
	private String memberNo;
	private String boardNo;
	private String reasonNo;
	private String content;
	private String reasonName;
	private String nick;
	private String categoryName;
	
	public ReportListVo() {
		super();
	}

	public ReportListVo(String no, String memberNo, String boardNo, String reasonNo, String content, String reasonName,
			String nick, String categoryName) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.reasonNo = reasonNo;
		this.content = content;
		this.reasonName = reasonName;
		this.nick = nick;
		this.categoryName = categoryName;
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

	public String getReasonNo() {
		return reasonNo;
	}

	public void setReasonNo(String reasonNo) {
		this.reasonNo = reasonNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReasonName() {
		return reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ReportListVo [no=" + no + ", memberNo=" + memberNo + ", boardNo=" + boardNo + ", reasonNo=" + reasonNo
				+ ", content=" + content + ", reasonName=" + reasonName + ", nick=" + nick + ", categoryName="
				+ categoryName + "]";
	}
	
}
