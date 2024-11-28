package com.java.vo;

import java.time.LocalDateTime;
import java.util.Date;

public class CallRecordVo {
	private Long id;
	private Long employeeId;
	private String employeeName;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private int totalTime;
	
	public CallRecordVo(Long id, Long employeeId, String employeeName, LocalDateTime startDateTime, LocalDateTime endDateTime,
			int totalTime) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.totalTime = totalTime;
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
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public CallRecordVo() {}
}
