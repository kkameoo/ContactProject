package com.java.vo;

public class RegionsVo {
	private Long id;
	private String name;
	private String age;
	private String number;
	private String departmentName;
	private String employeeRank;
	private String departmentNumber;
	private String regionsName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmployeeRank() {
		return employeeRank;
	}
	public void setEmployeeRank(String employeeRank) {
		this.employeeRank = employeeRank;
	}
	public String getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	public String getRegionsName() {
		return regionsName;
	}
	public void setRegionsName(String regionsName) {
		this.regionsName = regionsName;
	}
	
	public RegionsVo(Long id, String name, String age, String number, String departmentName, String employeeRank,
			String departmentNumber, String regionsName) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.number = number;
		this.departmentName = departmentName;
		this.employeeRank = employeeRank;
		this.departmentNumber = departmentNumber;
		this.regionsName = regionsName;
	}
	
	public RegionsVo() {}
}
