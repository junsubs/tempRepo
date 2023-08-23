package com.kh.app.member.vo;

public class MemberVo {
	
	private String no;
	private String memberCategoryNo;
	private String memberGradeNo;
	private String id;
	private String pwd;
	private String nick;
	private String enrollDate;
	private String withdrawalYn;
	private String updateDate;
	private String email;
	private String address;
	private String status;
	
	private String memberCategoryName;
	private String memberGradeName;
	private String memberGradeImg;
	public String getMemberGradeImg() {
		return memberGradeImg;
	}
	public void setMemberGradeImg(String memberGradeImg) {
		this.memberGradeImg = memberGradeImg;
	}
	private String totalAttend;
	
	public String getTotalAttend() {
		return totalAttend;
	}
	public void setTotalAttend(String totalAttend) {
		this.totalAttend = totalAttend;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMemberCategoryNo() {
		return memberCategoryNo;
	}
	public void setMemberCategoryNo(String memberCategoryNo) {
		this.memberCategoryNo = memberCategoryNo;
	}
	public String getMemberGradeNo() {
		return memberGradeNo;
	}
	public void setMemberGradeNo(String memberGradeNo) {
		this.memberGradeNo = memberGradeNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getWithdrawalYn() {
		return withdrawalYn;
	}
	public void setWithdrawalYn(String withdrawalYn) {
		this.withdrawalYn = withdrawalYn;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemberCategoryName() {
		return memberCategoryName;
	}
	public void setMemberCategoryName(String memberCategoryName) {
		this.memberCategoryName = memberCategoryName;
	}
	public String getMemberGradeName() {
		return memberGradeName;
	}
	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", memberCategoryNo=" + memberCategoryNo + ", memberGradeNo=" + memberGradeNo
				+ ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", enrollDate=" + enrollDate + ", withdrawalYn="
				+ withdrawalYn + ", updateDate=" + updateDate + ", email=" + email + ", address=" + address
				+ ", status=" + status + ", memberCategoryName=" + memberCategoryName + ", memberGradeName="
				+ memberGradeName + "]";
	}
	public MemberVo(String no, String memberCategoryNo, String memberGradeNo, String id, String pwd, String nick,
			String enrollDate, String withdrawalYn, String updateDate, String email, String address, String status,
			String memberCategoryName, String memberGradeName) {
		super();
		this.no = no;
		this.memberCategoryNo = memberCategoryNo;
		this.memberGradeNo = memberGradeNo;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.enrollDate = enrollDate;
		this.withdrawalYn = withdrawalYn;
		this.updateDate = updateDate;
		this.email = email;
		this.address = address;
		this.status = status;
		this.memberCategoryName = memberCategoryName;
		this.memberGradeName = memberGradeName;
	}
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
