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
		
		scanner.close();
	}
}

