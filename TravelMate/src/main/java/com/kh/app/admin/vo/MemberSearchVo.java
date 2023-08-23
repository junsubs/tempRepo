package com.kh.app.admin.vo;

public class MemberSearchVo {

	private String no;
	private String id;
	private String nick;
	private String status;
	private String gradeName;
	
	public MemberSearchVo() {
		super();
	}

	public MemberSearchVo(String no, String id, String nick, String status, String gradeName) {
		super();
		this.no = no;
		this.id = id;
		this.nick = nick;
		this.status = status;
		this.gradeName = gradeName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Override
	public String toString() {
		return "MemberSearchVo [no=" + no + ", id=" + id + ", nick=" + nick + ", status=" + status + ", gradeName="
				+ gradeName + "]";
	}
}
