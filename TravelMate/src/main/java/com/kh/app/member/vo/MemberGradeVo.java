package com.kh.app.member.vo;

public class MemberGradeVo {

	private String no;
	private String name;
	private String img;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "MemberGradeVo [no=" + no + ", name=" + name + ", img=" + img + "]";
	}
	public MemberGradeVo(String no, String name, String img) {
		super();
		this.no = no;
		this.name = name;
		this.img = img;
	}
	public MemberGradeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
