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
		searchEmployment();
	}
	

	private static void listEmployment() {
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.getList();
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("==========모든 연락처==========");
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %x\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
		
		System.out.println("====================");
	}
	
	private static void searchEmployment() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색어 입력: ");
		String keywordName = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(keywordName);
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("==========검색된 연락처==========");
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %x\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
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
		System.out.println("0.이름 \n2.전체목록 \n3.부서별 열람 \n4.지사별 열람 \n5.통화 \n6.최근기록 \n7.프로그램 종료 ");
		int num = scanner.nextInt();
		
		switch(num) {
		case 0 : {
			while(true) {
				System.out.println("이름을 입력하세요.");
				
				
			}
			
			
		}
		
		case 1 : {
			while(true) {
				System.out.println("이름을 입력하세요.");
				
//				for ()
				
				
			}
			
		}
		
		
		}
		
		
			
			
		
		
	
				}
				
			}
			
		
			
			
		
		
		 
		
		
		scanner.close();
	}
}

