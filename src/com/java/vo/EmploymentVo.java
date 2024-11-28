package com.java.vo;

public class EmploymentVo { // 필드선언
	private Long id;
	private String name;
	private String age;
	private String email;
	private String address;
	private Long departmentId;
	private String employeeRank;
	private Long salary;
	private String number;
	
	public Long getId() { //접근자 설정
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
	public String getEmployeeRank() {
		return employeeRank;
	}
	public void setEmployeeRank(String employeeRank) {
		this.employeeRank = employeeRank;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public EmploymentVo(Long id, String name, String age, String email, String address, Long departmentId, String employeeRank, Long salary,
			String number) { // 기본생성자
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.address = address;
		this.departmentId = departmentId;
		this.employeeRank = employeeRank;
		this.salary = salary;
		this.number = number;
	}
	
	public EmploymentVo() { // 생성자 생성
		
	}
	@Override
	public String toString() {
		return "id:" + id + ", name:" + name + ", age:" + age + ", email:" + email + ", address:"
				+ address + ", departmentId:" + departmentId + ", employeeRank:" + employeeRank + ", salary:" + salary
				+ ", number:" + number;
	}
	
	
		
	
}
