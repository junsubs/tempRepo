package com.kh.app.admin.util;

public class AttachmentVo {

	private String no;
	private String bno;
	private String originName;
	private String changeName;
	private String enrollDate;
	private String status;
	public AttachmentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttachmentVo(String no, String bno, String originName, String changeName, String enrollDate, String status) {
		super();
		this.no = no;
		this.bno = bno;
		this.originName = originName;
		this.changeName = changeName;
		this.enrollDate = enrollDate;
		this.status = status;
	}
	@Override
	public String toString() {
		return "AttachmentVo [no=" + no + ", bno=" + bno + ", originName=" + originName + ", changeName=" + changeName
				+ ", enrollDate=" + enrollDate + ", status=" + status + "]";
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
