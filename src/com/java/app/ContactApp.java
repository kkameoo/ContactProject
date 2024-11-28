package com.java.app; // 실제 실행클래스 , 프로그램 실행을 위한 첨가물 역할 ex )조건,출력물

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.java.dao.EmploymentDao;
import com.java.dao.impl.EmploymentImpl;
import com.java.vo.EmploymentVo;

public class ContactApp {

	public static void main(String[] args) {
//		listEmployment();
//		searchEmployment();
		
		System.out.println("====================");
		boolean flag = false;	
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			if (flag) {
				System.out.println("프로그램을 종료합니다.");
				 break;
			}
			System.out.println("-----연락처 프로그램-----");
			System.out.println("번호를 선택하세요.");
			System.out.println("0.종료 \n1.전체목록 \n2.검색 \n3.지사별 열람 \n4.통화 \n5.최근기록 \n6.프로그램 종료 ");
			int num = scanner.nextInt();
			
			switch(num) {
			case 0 : {
				flag = true;
				break;
			}
			case 1 : {
				listEmployment();				
			}
			case 2 : {
				searchEmployment();
				while(true) {
					
					System.out.println("-------------");
					System.out.println("0.뒤로가기 1.id입력");				
					Long num2 = scanner.nextLong();
					
					if (num2 == 0) {
						break;
					} else {
						searchId(num2);
					} 														
				}				
			}
			case 3 : {				
			}			
		}		
	}
		scanner.close();
	}
				
	private static void listEmployment() {
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.getList();
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("==========모든 연락처==========");
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
		
		System.out.println("====================");
	}
	
	private static void searchEmployment() { // 메서드 생성
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색어 입력: ");
		String keywordName = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(keywordName);
		Iterator<EmploymentVo> iter = list.iterator();
		
		
		System.out.println("==========검색된 연락처==========");
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
	}
		
		private static void searchId(Long id) {
			EmploymentDao dao = new EmploymentImpl();
			EmploymentVo vo = dao.getEmploymentById(id);
			System.out.printf("name : %s\n id : %d\n age : %s\n email : %s\n address : %s\n", vo.getName(), vo.getId(), vo.getAge(), vo.getEmail(), vo.getAddress());
			System.out.printf("부서번호 : %d\n 직급 : %s\n 연봉 : %d\n 전화번호 : %s\n  ", vo.getDepartmentId(),vo.getEmployeeRank(), vo.getSalary(), vo.getNumber());
		}
}

