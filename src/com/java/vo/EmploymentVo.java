package com.java.vo;

public class EmploymentVo {
	private Long id;
	private String age;
	private String email;
	private String address;
	private Long departmentId;
	private String rank;
	private Long salary;
	private String employeeNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public EmploymentVo(Long id, String age, String email, String address, Long departmentId, String rank, Long salary,
			String employeeNumber) {
		this.id = id;
		this.age = age;
		this.email = email;
		this.address = address;
		this.departmentId = departmentId;
		this.rank = rank;
		this.salary = salary;
		this.employeeNumber = employeeNumber;
	}
	
	public EmploymentVo() {
		
	}
		
}
