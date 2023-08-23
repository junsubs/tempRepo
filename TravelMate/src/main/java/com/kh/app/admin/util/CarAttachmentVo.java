package com.kh.app.admin.util;

public class CarAttachmentVo {
	
	private String no;
	private String carNo;
	private String title;
	private String deleteYn;
	private String enrollDate;
	private String changeName;
	private String originName;
	
	public CarAttachmentVo() {
		super();
	}

	public CarAttachmentVo(String no, String carNo, String title, String deleteYn, String enrollDate, String changeName,
			String originName) {
		super();
		this.no = no;
		this.carNo = carNo;
		this.title = title;
		this.deleteYn = deleteYn;
		this.enrollDate = enrollDate;
		this.changeName = changeName;
		this.originName = originName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	@Override
	public String toString() {
		return "CarAttachmentVo [no=" + no + ", carNo=" + carNo + ", title=" + title + ", deleteYn=" + deleteYn
				+ ", enrollDate=" + enrollDate + ", changeName=" + changeName + ", originName=" + originName + "]";
	}

}
