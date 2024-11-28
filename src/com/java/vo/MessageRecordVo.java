package com.java.vo;

import java.util.Date;

public class MessageRecordVo {
	
	private Long id;
	private Long employeeId;
	private String employeeName;
	private String employeeNumber;
	private String messageComment;
	private int readCheck;
	private String regDate;
	
	public MessageRecordVo(Long id, Long employeeId, String employeeName, String employeeNumber, String messageComment,
			int readCheck, String regDate) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeNumber = employeeNumber;
		this.messageComment = messageComment;
		this.readCheck = readCheck;
		this.regDate = regDate;
	}
	
	public MessageRecordVo(Long id, Long employeeId, String employeeName, String regDate, int readCheck) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.regDate = regDate;
		this.readCheck = readCheck;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getMessageComment() {
		return messageComment;
	}

	public void setMessageComment(String messageComment) {
		this.messageComment = messageComment;
	}

	public int getReadCheck() {
		return readCheck;
	}

	public void setReadCheck(int readCheck) {
		this.readCheck = readCheck;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "MessageRecordVo [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", readCheck=" + readCheck + ", regDate=" + regDate + "]";
	}
	
}
