package com.kh.app.board.vo;

public class BoardVo {
	
	private String no;
	private String boardCategoryNo;
	private String proCategoryNo;
	private String memberNo;
	private String boardImgNo;
	private String title;
	private String content;
	private String enrollDate;
	private String deleteYn;
	private String hit;
	private String uploadYn;
	private String modifyDate;
	
	private String memberNick;
	private String categoryName;
	private String boardImgTitle;
	
	public String getBoardImgTitle() {
		return boardImgTitle;
	}
	public void setBoardImgTitle(String boardImgTitle) {
		this.boardImgTitle = boardImgTitle;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getBoardCategoryNo() {
		return boardCategoryNo;
	}
	public void setBoardCategoryNo(String boardCategoryNo) {
		this.boardCategoryNo = boardCategoryNo;
	}
	public String getProCategoryNo() {
		return proCategoryNo;
	}
	public void setProCategoryNo(String proCategoryNo) {
		this.proCategoryNo = proCategoryNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getBoardImgNo() {
		return boardImgNo;
	}
	public void setBoardImgNo(String boardImgNo) {
		this.boardImgNo = boardImgNo;
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
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getUploadYn() {
		return uploadYn;
	}
	public void setUploadYn(String uploadYn) {
		this.uploadYn = uploadYn;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", boardCategoryNo=" + boardCategoryNo + ", proCategoryNo=" + proCategoryNo
				+ ", memberNo=" + memberNo + ", boardImgNo=" + boardImgNo + ", title=" + title + ", content=" + content
				+ ", enrollDate=" + enrollDate + ", deleteYn=" + deleteYn + ", hit=" + hit + ", uploadYn=" + uploadYn
				+ ", modifyDate=" + modifyDate + ", memberNick=" + memberNick + ", categoryName=" + categoryName + "]";
	}
	public BoardVo(String no, String boardCategoryNo, String proCategoryNo, String memberNo, String boardImgNo,
			String title, String content, String enrollDate, String deleteYn, String hit, String uploadYn,
			String modifyDate, String memberNick, String categoryName) {
		super();
		this.no = no;
		this.boardCategoryNo = boardCategoryNo;
		this.proCategoryNo = proCategoryNo;
		this.memberNo = memberNo;
		this.boardImgNo = boardImgNo;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.deleteYn = deleteYn;
		this.hit = hit;
		this.uploadYn = uploadYn;
		this.modifyDate = modifyDate;
		this.memberNick = memberNick;
		this.categoryName = categoryName;
	}
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
