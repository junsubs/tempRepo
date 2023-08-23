package com.kh.app.admin.vo;

public class AdBannerVo {
	
	private String no;
	private String name;
	private String image;
	private String nick;
	private String souvenirName;
	private String souvenirNo;
	
	public AdBannerVo() {
		super();
	}

	public AdBannerVo(String no, String name, String image, String nick, String souvenirName, String souvenirNo) {
		super();
		this.no = no;
		this.name = name;
		this.image = image;
		this.nick = nick;
		this.souvenirName = souvenirName;
		this.souvenirNo = souvenirNo;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSouvenirName() {
		return souvenirName;
	}

	public void setSouvenirName(String souvenirName) {
		this.souvenirName = souvenirName;
	}

	public String getSouvenirNo() {
		return souvenirNo;
	}

	public void setSouvenirNo(String souvenirNo) {
		this.souvenirNo = souvenirNo;
	}

	@Override
	public String toString() {
		return "AdBannerVo [no=" + no + ", name=" + name + ", image=" + image + ", nick=" + nick + ", souvenirName="
				+ souvenirName + ", souvenirNo=" + souvenirNo + "]";
	}

	
}
