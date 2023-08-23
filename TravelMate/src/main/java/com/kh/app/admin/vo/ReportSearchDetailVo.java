package com.kh.app.admin.vo;

public class ReportSearchDetailVo {
	
	private String no;
	private String reportListNo;
	private String name;
	private String memberId;
	private String enrollDate;
	private String cancelEnrollDate;
	private String count;
	
	public ReportSearchDetailVo() {
		super();
	}

	public ReportSearchDetailVo(String no, String reportListNo, String name, String memberId, String enrollDate,
			String cancelEnrollDate, String count) {
		super();
		this.no = no;
		this.reportListNo = reportListNo;
		this.name = name;
		this.memberId = memberId;
		this.enrollDate = enrollDate;
		this.cancelEnrollDate = cancelEnrollDate;
		this.count = count;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getReportListNo() {
		return reportListNo;
	}

	public void setReportListNo(String reportListNo) {
		this.reportListNo = reportListNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getCancelEnrollDate() {
		return cancelEnrollDate;
	}

	public void setCancelEnrollDate(String cancelEnrollDate) {
		this.cancelEnrollDate = cancelEnrollDate;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReportSearchDetailVo [no=" + no + ", reportListNo=" + reportListNo + ", name=" + name + ", memberId="
				+ memberId + ", enrollDate=" + enrollDate + ", cancelEnrollDate=" + cancelEnrollDate + ", count="
				+ count + "]";
	}

}
