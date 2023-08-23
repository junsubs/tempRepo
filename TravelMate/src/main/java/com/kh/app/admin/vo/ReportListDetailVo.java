package com.kh.app.admin.vo;

public class ReportListDetailVo {

	private String no;
	private String memberNo;
	private String memberId;
	private String memberNick;
	private String boardName;
	private String content;
	private String reasonName;
	
	public ReportListDetailVo() {
		super();
	}

	public ReportListDetailVo(String no, String memberNo, String memberId, String memberNick, String boardName,
			String content, String reasonName) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNick = memberNick;
		this.boardName = boardName;
		this.content = content;
		this.reasonName = reasonName;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	@Override
	public String toString() {
		return "ReportListDetailVo [no=" + no + ", memberNo=" + memberNo + ", memberId=" + memberId + ", memberNick="
				+ memberNick + ", boardName=" + boardName + ", content=" + content + ", reasonName=" + reasonName + "]";
	}

}
