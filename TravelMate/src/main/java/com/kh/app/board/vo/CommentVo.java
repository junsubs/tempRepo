package com.kh.app.board.vo;

public class CommentVo {

	private String no;
	private String memberNo;
	private String memberNick;
	private String boardNo;
	private String qnaNo;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String deleteYn;
	public CommentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentVo(String no, String memberNo, String memberNick, String boardNo, String qnaNo, String content,
			String enrollDate, String modifyDate, String deleteYn) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.memberNick = memberNick;
		this.boardNo = boardNo;
		this.qnaNo = qnaNo;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteYn = deleteYn;
	}
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", memberNo=" + memberNo + ", memberNick=" + memberNick + ", boardNo=" + boardNo
				+ ", qnaNo=" + qnaNo + ", content=" + content + ", enrollDate=" + enrollDate + ", modifyDate="
				+ modifyDate + ", deleteYn=" + deleteYn + "]";
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
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
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
	
	
	
	
}
