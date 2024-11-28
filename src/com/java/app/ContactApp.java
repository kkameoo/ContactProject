package com.java.app;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.java.dao.EmploymentDao;
import com.java.dao.impl.EmploymentImpl;
import com.java.vo.EmploymentVo;

public class ContactApp {

	public static void main(String[] args) {
//		listEmployment();
//		DetailListEmployment();
//		searchEmploymentName();
//		searchEmploymentId();
//		searchEmploymentDepartmentId();
//		searchEmploymentNumber();
//		insertEmployment();
//		updateEmployment();
//		deleteEmployment();
	}
	
//	아이디, 이름, 나이, 전화번호, 이메일, 주소, 부서번호, 직급, 월급 순
	
	private static void listEmployment() {
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.getList();
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("================================ 모든 연락처 ================================");
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id: %d\t   이름: %s\t  나이: %s\t 전화번호: %s\t    직급: %s\n", 
					vo.getId(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getEmployeeRank());
		}
		
		System.out.println("==========================================================================");
	}
	
	private static void DetailListEmployment() {
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.getList();
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("================================ 모든 상세 연락처 ================================");
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id: %d\t  이름: %s\t나이: %s\t   전화번호: %s\t    이메일: %s\t   주소: %s\t 부서 id: %d\t직급: %s\t    월급: %d\n", 
					vo.getId(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getEmail(), vo.getAddress(), vo.getDepartmentId(), vo.getEmployeeRank(), vo.getSalary());
		}
		
		System.out.println("==========================================================================");
	}
	
	private static void searchEmploymentName() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 이름 : ");
		String keywordName = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(keywordName);
		Iterator<EmploymentVo> iter = list.iterator();
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("검색된 연락처 ->   이름: %s\t  id: %d\t   나이: %s\t전화번호: %s\t 직급: %s\n", 
					vo.getName(), vo.getId(), vo.getAge(), vo.getNumber(), vo.getEmployeeRank());
		}
		scanner.close();
	}
	
	
	private static void searchEmploymentId() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 id : ");
		Long keyId = scanner.nextLong();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.searchId(keyId);
		Iterator<EmploymentVo> iter = list.iterator();
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("검색된 연락처 ->   id: %d\t 이름: %s\t나이: %s\t전화번호: %s\t 직급: %s\n", 
					vo.getId(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getEmployeeRank());
		}
		scanner.close();
	}
	
	private static void searchEmploymentDepartmentId() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 부서 id : ");
		Long keyDptId = scanner.nextLong();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.searchDptId(keyDptId);
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("======================================== 검색된 연락처 =========================================");
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("부서 id: %d\tid: %d\t     이름: %s\t    나이: %s\t 전화번호: %s\t    직급: %s\n", 
					vo.getDepartmentId(), vo.getId(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getEmployeeRank());
		}
		scanner.close();
	}
	
	private static void searchEmploymentNumber() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 전화번호 : ");
		String keyNumber = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.searchNumber(keyNumber);
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("==================================== 검색된 연락처 =====================================");
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			
			System.out.printf("전화번호: %s\t   id: %d\t 이름: %s\t나이: %s\t      부서id: %d\t   직급: %s\n", 
					vo.getNumber(), vo.getId(), vo.getName(), vo.getAge(), vo.getDepartmentId(), vo.getEmployeeRank());
		}
		scanner.close();
	}
	
	private static void insertEmployment() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = scanner.next();
		System.out.print("나이 : ");
		String age = scanner.next();
		System.out.print("전화번호 : ");
		String number = scanner.next();
		System.out.print("주소 : ");
		String address = scanner.next();
		System.out.print("부서 id : ");
		Long departmentId = scanner.nextLong();
		System.out.print("직급 : ");
		String employeeRank = scanner.next();
		System.out.print("월급 : ");
		Long salary = scanner.nextLong();
		
		
		EmploymentVo vo = new EmploymentVo(name, age, number, address, departmentId, employeeRank, salary);
		
		EmploymentDao dao = new EmploymentImpl();
		boolean success = dao.insert(vo);
		
		System.out.println("연락처가 " + (success ? "추가되었습니다" : "추가되지않았습니다"));
		scanner.close();
	}
	
	private static void updateEmployment() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("수정할 연락처의 이름 : ");
		String updateId = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(updateId);
		Iterator<EmploymentVo> iter = list.iterator();
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("수정할 연락처 ->\tid: %d    이름: %s    나이: %s    전화번호: %s    이메일: %s    주소: %s    부서 id: %d    직급: %s    월급: %d\n", 
					vo.getId(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getEmail(), vo.getAddress(), vo.getDepartmentId(), vo.getEmployeeRank(), vo.getSalary());
		}
		System.out.println();
		System.out.println("=================== 수정하기 ===================");
		
		System.out.print("수정할 id : ");
		Long id = scanner.nextLong();
		System.out.print("수정할 이름 : ");
		String name = scanner.next();
		System.out.print("수정할 나이 : ");
		String age = scanner.next();
		System.out.print("수정할 전화번호 : ");
		String number = scanner.next();
		System.out.print("수정할 이메일 : ");
		String email = scanner.next();
		System.out.print("수정할 주소 : ");
		String address = scanner.next();
		System.out.print("수정할 부서 id : ");
		Long departmentId = scanner.nextLong();
		System.out.print("수정할 직급 : ");
		String employeeRank = scanner.next();
		System.out.print("수정할 월급 : ");
		Long salary = scanner.nextLong();	
		
		EmploymentVo vo = new EmploymentVo(id, name, age, number, email, address, departmentId, employeeRank, salary);
		EmploymentDao dao2 = new EmploymentImpl();
		boolean success = dao2.update(vo);
		
		System.out.println("연락처가 " + (success ? "수정되었습니다" : "수정되지않았습니다"));
		scanner.close();
	}
	
	private static void deleteEmployment() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("삭제할 연락처의 이름 : ");
		String name = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		boolean success = dao.delete(name);
		
		System.out.println("연락처가 " + (success ? "삭제되었습니다" : "삭제되지않았습니다"));
		scanner.close();
	}
}

