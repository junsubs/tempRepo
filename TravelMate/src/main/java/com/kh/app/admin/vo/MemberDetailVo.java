package com.kh.app.admin.vo;

public class MemberDetailVo {
	
	private String no;
	private String id;
	private String nick;
	private String email;
	private String address;
	private String name;
	private String enrollDate;
	private String updateDate;
	private String status;
	private String withdrawalYn;
	
	public MemberDetailVo() {
		super();
	}

	public MemberDetailVo(String no, String id, String nick, String email, String address, String name,
			String enrollDate, String updateDate, String status, String withdrawalYn) {
		super();
		this.no = no;
		this.id = id;
		this.nick = nick;
		this.email = email;
		this.address = address;
		this.name = name;
		this.enrollDate = enrollDate;
		this.updateDate = updateDate;
		this.status = status;
		this.withdrawalYn = withdrawalYn;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWithdrawalYn() {
		return withdrawalYn;
	}

	public void setWithdrawalYn(String withdrawalYn) {
		this.withdrawalYn = withdrawalYn;
	}

	@Override
	public String toString() {
		return "MemberDetailVo [no=" + no + ", id=" + id + ", nick=" + nick + ", email=" + email + ", address="
				+ address + ", name=" + name + ", enrollDate=" + enrollDate + ", updateDate=" + updateDate + ", status="
				+ status + ", withdrawalYn=" + withdrawalYn + "]";
	}

}
